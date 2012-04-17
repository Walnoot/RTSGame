package walnoot.rtsgame.map.buildings.buttons;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import walnoot.rtsgame.Images;
import walnoot.rtsgame.map.Map;
import walnoot.rtsgame.map.buildings.RoadBuilding;

public class RoadButton extends BuildingButtons {
	
	BufferedImage roadButton;
	private Point placeOnScreen;
	private int widthButton;
	private int heightButton;
	public Map map;
	private boolean isSelected = false;
	
	public RoadButton(Point placeOnScreen, int widthButton, int heightButton, Map map){
		this.map = map;
		this.placeOnScreen = placeOnScreen;
		this.widthButton = widthButton;
		this.heightButton = heightButton;
		try{
			roadButton = Images.buttons[3][0];
		}catch(Exception e){
			System.out.println("roadbutton picture not available?!");
		}
		
	}
	
	public void render(Graphics g){
		g.drawImage(roadButton, placeOnScreen.x, placeOnScreen.y, widthButton, heightButton, null);
		if(isSelected) g.fillRect(placeOnScreen.x, placeOnScreen.y, 4, 4);
	}
	
	public void update(Point mousePos, boolean mouseIsDown, int translationX, int translationY, boolean isOnBar){
		if(isSelected && mouseIsDown){
			if(!isOnBar){
				int xPos = screenToMap(translationX, translationY, mousePos).x;
				int yPos = screenToMap(translationX, translationY, mousePos).y;
				map.addBuilding(new RoadBuilding(xPos, yPos, map), xPos, yPos);
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
