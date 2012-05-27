package walnoot.rtsgame.popups;

import java.awt.Graphics;

import walnoot.rtsgame.Images;
import walnoot.rtsgame.InputHandler;
import walnoot.rtsgame.map.entities.Entity;

public abstract class Popup {
	protected final InputHandler input;
	protected final Entity owner;
	
	/**
	 * @param input Inputhandler object
	 * @param owner The owner of this PopUp
	 */
	public Popup(InputHandler input, Entity owner){
		this.input = input;
		this.owner = owner;
	}
	
	public abstract void render(Graphics g);
	public abstract void update(int translationX, int translationY);
	public abstract void onLeftClick();
	public abstract boolean isInPopup();
	
	protected void drawBox(Graphics g, int width, int height, int screenX, int screenY){
		for(int x = 0; x < width; x++){
				int xp = x; //x coordinate on gui.png
				if(x == width - 1) xp = 2; 
				else if(x > 0) xp = 1;
				
			for(int y = 0; y < height; y++){
				int yp = y; //y coordinate on gui.png
				if(y == height - 1) yp = 2; 
				else if(y > 0) yp = 1;
				
				g.drawImage(Images.gui[xp][yp], x * 16 + screenX, y * 16 + screenY, null);
			}
		}
	}
	
	protected void drawBox(Graphics g, int width, int height){
		drawBox(g, width, height, getScreenX(), getScreenY());
	}
	
	public Entity getOwner(){
		return owner;
	}
	
	protected int getScreenX(){
		return owner.getScreenX();
	}
	
	protected int getScreenY(){
		return owner.getScreenY();
	}
}
