package walnoot.rtsgame.map.buildings.buttons;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import walnoot.rtsgame.Images;
import walnoot.rtsgame.map.Map;

public class TrashButton extends BuildingButton {
	BufferedImage trashButton;
	private Point placeOnScreen;
	private int widthButton;
	private int heightButton;
	public Map map;
	private boolean isSelected = false;
	
	public TrashButton(Point placeOnScreen, int widthButton, int heightButton, Map map){
		this.map = map;
		this.placeOnScreen = placeOnScreen;
		this.widthButton = widthButton;
		this.heightButton = heightButton;
		try{
			trashButton = Images.buttons[1][0];
		}catch(Exception e){
			System.out.println("roadbutton picture not available?!");
		}
		
	}
	
	public void render(Graphics g){
		g.drawImage(trashButton, placeOnScreen.x, placeOnScreen.y, widthButton, heightButton, null);
		if(isSelected) g.fillRect(placeOnScreen.x, placeOnScreen.y, 4, 4);
	}
	
	public void update(Point mousePos, boolean mouseIsDown, int translationX, int translationY, boolean isOnBar){
		if(isSelected && mouseIsDown){
			if(!isOnBar){
				int xPos = screenToMap(translationX, translationY, mousePos).x;
				int yPos = screenToMap(translationX, translationY, mousePos).y;
				map.deleteBuilding(xPos, yPos);
			}
		}
	}
	
	public void isSelected(){
		isSelected = true;
	}
	
	public void isDeselected(){
		isSelected = false;
	}
}
