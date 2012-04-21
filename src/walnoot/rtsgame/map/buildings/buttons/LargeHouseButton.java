package walnoot.rtsgame.map.buildings.buttons;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import walnoot.rtsgame.Images;
import walnoot.rtsgame.map.Map;
import walnoot.rtsgame.map.buildings.Building;
import walnoot.rtsgame.map.buildings.LargeHouseBuilding;
import walnoot.rtsgame.map.buildings.NoBuilding;

public class LargeHouseButton extends BuildingButton {
	BufferedImage largeHouseButton;
	private Point placeOnScreen;
	private int widthButton;
	private int heightButton;
	public Map map;
	private boolean isSelected = false;
	private Building largeHouse;
	
	public LargeHouseButton(Point placeOnScreen, int widthButton, int heightButton, Map map){
		this.map = map;
		this.placeOnScreen = placeOnScreen;
		this.widthButton = widthButton;
		this.heightButton = heightButton;
		try{
			largeHouseButton = Images.buttons[2][0];
		}catch(Exception e){
			System.out.println("largehousebutton picture not available?!");
		}
		largeHouse = new LargeHouseBuilding();
	}
	
	public void render(Graphics g){
		g.drawImage(largeHouseButton, placeOnScreen.x, placeOnScreen.y, widthButton, heightButton, null);
		if(isSelected) g.fillRect(placeOnScreen.x, placeOnScreen.y, 4, 4);
	}
	
	public void update(Point mousePos, boolean mouseIsDown, int translationX, int translationY, boolean isInBar){
		if(isSelected && mouseIsDown){
			int xPos = screenToMap(translationX, translationY, mousePos).x;
			int yPos = screenToMap(translationX, translationY, mousePos).y;
			if(!isInBar){
				if(map.getBuilding(xPos, yPos) == null && map.getBuilding(xPos - 1, yPos) == null && map.getBuilding(xPos, yPos - 1) == null && map.getBuilding(xPos - 1, yPos - 1) == null){
					map.addBuilding(largeHouse, xPos, yPos);
					map.addBuilding(new NoBuilding(13), xPos - 1, yPos);
					map.addBuilding(new NoBuilding(10), xPos, yPos - 1);
					map.addBuilding(new NoBuilding(14), xPos - 1, yPos - 1);
				}
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
