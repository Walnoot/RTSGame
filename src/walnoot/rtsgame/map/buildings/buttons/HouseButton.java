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
import walnoot.rtsgame.map.buildings.NoBuilding;
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
			houseButton = ImageIO.read(this.getClass().getResource("/buttonhouse2.png"));
		}catch(Exception e){
			System.out.println("housebutton picture not available?!");
		}
		house = new HouseBuilding();
	}
	
	public void render(Graphics g){
		g.drawImage(houseButton,placeOnScreen.x,placeOnScreen.y,widthButton,heightButton ,null);
		if(isSelected)g.fillRect( placeOnScreen.x, placeOnScreen.y, 4, 4);
	}
	
	public void update(Point mousePos, boolean mouseIsDown,int translationX,int  translationY, boolean isInBar){
		if(isSelected && mouseIsDown){
			//System.out.println(mousePos);
			if(!isInBar){
				map.addBuilding(house, screenToMap(translationX,translationY, mousePos).x, screenToMap(translationX,translationY,mousePos).y);
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