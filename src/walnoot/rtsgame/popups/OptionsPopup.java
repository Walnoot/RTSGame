package walnoot.rtsgame.popups;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import walnoot.rtsgame.InputHandler;
import walnoot.rtsgame.map.entities.Entity;
import walnoot.rtsgame.map.entities.MovingEntity;
import walnoot.rtsgame.screen.RTSFont;
import walnoot.rtsgame.screen.Screen;

public class OptionsPopup extends Popup {
	private final ArrayList<Option> options = new ArrayList<Option>();
	private int width, height;
	int indexSelected = -1;
	int indexHighlighted = -1;
	int screenX = 0, screenY = 0;

	public OptionsPopup(InputHandler input, Entity owner, Option...options){
		super(input, owner);
		
		if(owner instanceof MovingEntity){
			if (((MovingEntity) owner).getSelectedOption() != -1){
				indexSelected = ((MovingEntity) owner).getSelectedOption();
			}
		}
		
		for(int i = 0; i < options.length; i++){
			int lineWidth = Screen.font.getLineWidth(options[i].getName());
			if(lineWidth > width) width = lineWidth;
			this.options.add(options[i]);
		}
		
		height = RTSFont.HEIGHT *( options.length+1);
		System.out.println(width);
	}


	public void render(Graphics g){
		g.setColor(Color.BLACK);
		drawBox(g, width / 16 + 3, height / 16 + 3);
		
		for(int i = 0; i < options.size(); i++){
			if(i == indexSelected){
				options.get(i).renderInColor(g, this, i, Color.BLUE);
			}else if(i == indexHighlighted){
				options.get(i).renderInColor(g, this, i, Color.RED);
			}
			
			options.get(i).render(g,this, i);
		}
		
	}
	
	public void update(int translationX, int translationY){
		int mouseY = input.getMouseY();
		
		screenX = getScreenX() + translationX;
		screenY = getScreenY() + translationY;
		
		if(isInPopup()){
			indexHighlighted = (mouseY - 16 - screenY)/RTSFont.HEIGHT;
		}else{
			indexHighlighted = -1;
		}
		
	}
	
	public void addOption(Option option){
		options.add(option);
		int lineWidth = Screen.font.getLineWidth(option.getName());
		if(lineWidth > width) width = lineWidth;
	}
	
	public boolean isInPopup(){
		/*if(input.getMouseY() > screenY + 16 && input.getMouseY() < screenY + 16 + height && input.getMouseX() > screenX +16 && input.getMouseX() < screenX +16 + width){
			return true;
		}
		return false;*/
		
		//TODO: dit laten werken
		return true;
	}
	
	public Option getOption(int index){
		if(index < options.size() && index >= 0) return options.get(index);
		else return null;
		
	}

	public void onLeftClick() {
		if(isInPopup()){
			indexSelected = (input.getMouseY() - 16 - screenY)/RTSFont.HEIGHT;
			if(getOption(indexSelected) != null){	
				getOption(indexSelected).onClick();
			}
			if(owner instanceof MovingEntity)
				((MovingEntity) owner).setSelectedOption(indexSelected);
		}else{
			indexSelected = -1;
		}
	}
}
