package walnoot.rtsgame.map.buildings.buttons;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import walnoot.rtsgame.*;
import walnoot.rtsgame.map.*;
import walnoot.rtsgame.map.buildings.*;



public class TrashButton extends BuildingButtons {
	BufferedImage trashButton;
	private Point placeOnScreen;
	private int widthButton;
	private int heightButton;
	public Map map;
	private boolean isSelected=false;
	public TrashButton(Point placeOnScreen, int widthButton, int heightButton,Map map) {
		this.map = map;
		this.placeOnScreen = placeOnScreen;
		this.widthButton = widthButton;
		this.heightButton = heightButton;
		try{
			trashButton = ImageIO.read(this.getClass().getResource("/buttontrash.png"));
		}catch(Exception e){
			System.out.println("roadbutton picture not available?!");
		}
		
	}
	
	public void render(Graphics g){
		g.drawImage(trashButton,placeOnScreen.x,placeOnScreen.y,widthButton,heightButton ,null);
		if(isSelected)g.fillRect( placeOnScreen.x, placeOnScreen.y, 4, 4);
	}
	
	public void update(Point mousePos, boolean mouseIsDown, Point translation , boolean isOnBar){
		if(isSelected && mouseIsDown){
			if(!isOnBar){
				map.addBuilding(new NoBuilding(), screenToMap(translation, mousePos).x, screenToMap(translation,mousePos).y);
			}
		}
	}
	
	public Point screenToMap(Point translation, Point e){
		int mouseX = (((int)e.getX()) - 32) / RTSComponent.SCALE - translation.x;
		int mouseY = ((int)e.getY()) / RTSComponent.SCALE - translation.y;
		
		int x = (int) ((mouseY / 16.0) - (mouseX / 32.0));
		int y = (int) ((mouseY / 16.0) + (mouseX / 32.0));
		return new Point(x,y);
		
	}
	
	public void isSelected(){
		isSelected = true;
	}
	
	public void isDeselected(){
		isSelected = false;
	}
}
