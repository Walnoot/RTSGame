package walnoot.rtsgame.popups;

import java.awt.Color;
import java.awt.Graphics;

import walnoot.rtsgame.InputHandler;
import walnoot.rtsgame.map.entities.Entity;
import walnoot.rtsgame.screen.RTSFont;
import walnoot.rtsgame.screen.Screen;

public class TextPopup extends Popup {
	private final String[] text;
	private int width, height;

	public TextPopup(InputHandler input, Entity owner, String...text){
		super(input, owner);
		this.text = text;
		
		for(String line: text){
			int lineWidth = Screen.font.getLineWidth(line);
			
			if(lineWidth > width) width = lineWidth;
		}
		
		height = RTSFont.HEIGHT * text.length;
	}
	
	public void render(Graphics g){
		drawBox(g, width / 16 + 3, height / 16 + 3);
		
		g.setColor(Color.BLACK);
		
		for(int i = 0; i < text.length; i++){
			//g.drawString(text[i], getscreenX() + 16, getScreenY() + 16 + i * RTSFont.HEIGHT);
			Screen.font.drawLine(g, text[i], getScreenX() + 16, getScreenY() + 16 + i * RTSFont.HEIGHT);
		}
	}
	
	public void update(int translationX, int translationY){
		
	}

	public void onLeftClick() {}
	public boolean isInPopup(int x , int y){
		return false;}
}
