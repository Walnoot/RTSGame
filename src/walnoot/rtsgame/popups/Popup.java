package walnoot.rtsgame.popups;

import java.awt.Graphics;

import walnoot.rtsgame.Images;
import walnoot.rtsgame.InputHandler;
import walnoot.rtsgame.map.entities.Entity;

public abstract class Popup {
	public static final int EMPTY_SPACE = 32;
	
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
	public abstract boolean isInPopup(int x, int y);
	
	/**
	 * @param g
	 * @param width width in pixels
	 * @param height height in pixels
	 * @param screenX
	 * @param screenY
	 */
	protected void drawBox(Graphics g, int width, int height, int screenX, int screenY){
		/*for(int x = 0; x < width; x++){
			int xp = x; //x coordinate on gui.png
			if(x == width - 1) xp = 2; 
			else if(x > 0) xp = 1;
			
			for(int y = 0; y < height; y++){
				int yp = y; //y coordinate on gui.png
				if(y == height - 1) yp = 2; 
				else if(y > 0) yp = 1;
				
				g.drawImage(Images.gui[xp][yp], x * 16 + screenX, y * 16 + screenY, null);
			}
		}*/
		
		int imageWidth = Images.gui[0][0].getWidth();
		int imageHeight = Images.gui[0][0].getHeight();
		
		//renderen achtergrond
		
		g.setClip(screenX + imageWidth / 2, screenY + imageHeight / 2, width - imageWidth, height - imageHeight);
		//g.setClip(screenX, screenY, width, height);
		
		for(int x = 0; x < width / imageWidth + 1; x++){
			for(int y = 0; y < height / imageHeight + 1; y++){
				g.drawImage(Images.gui[1][1], screenX + x * imageWidth, screenY + y * imageHeight, null);
			}
		}
		
		//renderen rand linkerbovenhoek
		
		g.setClip(screenX, screenY, width - imageWidth, height - imageHeight);
		
		for(int x = 0; x < width / imageWidth; x++){
			for(int y = 0; y < height / imageHeight; y++){
				int imageX = x, imageY = y;
				if(x > 0) imageX = 1;
				if(y > 0) imageY = 1;
				
				if(imageX == 1 && imageY == 1) continue;
				
				g.drawImage(Images.gui[imageX][imageY], screenX + x * imageWidth, screenY + y * imageHeight, null);
			}
		}
		
		g.setClip(screenX, screenY, width - imageWidth, height);
		
		for(int x = 0; x < width / imageWidth; x++){
			int imageX = x, imageY = 2;
			if(x > 0) imageX = 1;
			
			g.drawImage(Images.gui[imageX][imageY], screenX + x * imageWidth, screenY + height - imageHeight, null);
		}
		
		g.setClip(screenX, screenY, width, height - imageHeight);
		
		for(int y = 0; y < width / imageWidth; y++){
			int imageX = 2, imageY = y;
			if(y > 0) imageY = 1;
			
			g.drawImage(Images.gui[imageX][imageY], screenX + width - imageWidth, screenY + y * imageHeight, null);
		}
		
		g.setClip(null);
		
		g.drawImage(Images.gui[2][2], screenX + width - imageWidth, screenY + height - imageHeight, null);
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
