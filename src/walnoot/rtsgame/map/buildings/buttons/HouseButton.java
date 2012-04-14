package walnoot.rtsgame.map.buildings.buttons;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import walnoot.rtsgame.RTSComponent;
import walnoot.rtsgame.map.Map;
import walnoot.rtsgame.map.buildings.Building;
import walnoot.rtsgame.map.buildings.HouseBuilding;
import walnoot.rtsgame.screen.Screen;

public class HouseButton extends BuildingButtons {
BufferedImage houseButton;
private Point placeOnScreen;
private int widthButton;
private int heightButton;
public Map map;
private boolean isSelected=false;
private Building house;
	
	public HouseButton(Point placeOnScreen, int widthButton, int heightButton,Map map) {
		this.map = map;
		this.placeOnScreen = placeOnScreen;
		this.widthButton = widthButton;
		this.heightButton = heightButton;
		try{
			houseButton = ImageIO.read(this.getClass().getResource("/buttonhouse.png"));
		}catch(Exception e){
			System.out.println("housebutton picture not available?!");
		}
		house = new HouseBuilding();
	}
	
	public void render(Graphics g){
		g.drawImage(houseButton,placeOnScreen.x,placeOnScreen.y,widthButton,heightButton ,null);
		if(isSelected)g.fillRect( placeOnScreen.x, placeOnScreen.y, 4, 4);
	}
	
	public void update(Point mousePos, boolean mouseIsDown,Point translation, boolean isInBar){
		if(isSelected && mouseIsDown){
			if(!isInBar){
				map.addBuilding(house, screenToMap(translation, mousePos).x, screenToMap(translation,mousePos).y);
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
