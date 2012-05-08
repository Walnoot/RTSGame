package walnoot.rtsgame.screen.menus;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import walnoot.rtsgame.InputHandler;
import walnoot.rtsgame.RTSComponent;
import walnoot.rtsgame.screen.Screen;

public abstract class MenuScreen extends Screen {
	protected int selectedButton = 0;
	
	public MenuScreen(RTSComponent component, InputHandler input){
		super(component, input);
	}
	
	public void render(Graphics g){
		for(MenuButton b: getButtons()){
			if(b == null) System.out.println("uch");
			b.render(g);
		}
	}
	
	public void update(){
		for(MenuButton b: getButtons()){
			b.setMouseLocation(super.getMouseLocation());
		}
	}
	
	protected abstract MenuButton[] getButtons();
	
	public abstract void buttonPressed(MenuButton menuButton);
	
	public void mouseReleased(MouseEvent e){
		for(MenuButton b: getButtons()){
			b.mouseReleased(e);
		}
	}
	
	public void mousePressed(MouseEvent e){
		for(MenuButton b: getButtons()){
			b.mousePressed(e);
		}
	}
}
