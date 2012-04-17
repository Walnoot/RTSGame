package walnoot.rtsgame.map.buildings;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import walnoot.rtsgame.map.*;
import walnoot.rtsgame.map.buildings.buttons.*;
import walnoot.rtsgame.map.entities.TestEntity;

public class SelectBar {
	Point posBar = new Point(10, 20);
	private int width = 200, height = 20, widthButton = 15, heightButton = 15;
	BufferedImage bar;
	Map map;
	ArrayList<BuildingButtons> buttons = new ArrayList<BuildingButtons>();
	
	private BuildingButtons house;
	private BuildingButtons road;
	private BuildingButtons trash;
	private BuildingButtons largeHouse;
	
	private boolean inBar = false;
	
	public SelectBar(Map map){
		this.map = map;
		try{
			bar = ImageIO.read(this.getClass().getResource("/selectbar2.png"));
		}catch(Exception e){
			System.out.println("bar image not found!");
			e.printStackTrace();
		}
		house = new HouseButton(new Point(posBar.x + 20, posBar.y + 2), widthButton, heightButton, map);
		road = new RoadButton(new Point(posBar.x + 20 + widthButton, posBar.y + 2), widthButton, heightButton, map);
		trash = new TrashButton(new Point(posBar.x + 20 + widthButton * 2, posBar.y + 2), widthButton, heightButton, map);
		largeHouse = new LargeHouseButton(new Point(posBar.x + 20 + widthButton * 3, posBar.y + 2), widthButton, heightButton, map);
		buttons.add(house);
		buttons.add(road);
		buttons.add(trash);
		buttons.add(largeHouse);
	}
	
	public void update(Point mousePos, boolean mouseIsDown, int translationX, int translationY){
		int indexSelected;
		if(isOnButtons(mousePos) && mouseIsDown){
			int x = ((mousePos.x / 2 - posBar.x - 20));
			indexSelected = ((x - (x % widthButton)) / widthButton);
			for(BuildingButtons b: buttons){
				b.isDeselected();
			}
			buttons.get(indexSelected).isSelected();
			
		}else if(isInBar(mousePos) && mouseIsDown){
			for(BuildingButtons b: buttons){
				b.isDeselected();
			}
		}
		for(BuildingButtons b: buttons){
			b.update(mousePos, mouseIsDown, translationX, translationY, isInBar(mousePos));
		}
		
	}
	
	public boolean isInBar(Point mousePos){
		if(mousePos.x / 2 > posBar.x && mousePos.x / 2 < (posBar.x + width) && mousePos.y / 2 > posBar.y && (mousePos.y / 2 < posBar.y + height)){
			return true;
		}
		return false;
		
	}
	
	public boolean isOnButtons(Point mousePos){
		if(mousePos.x / 2 > posBar.x + 20 && mousePos.x / 2 < (posBar.x + buttons.size() * 15 + 20) && mousePos.y / 2 > posBar.y + 2 && (mousePos.y / 2 < posBar.y + height - 3)){
			
			return true;
		}
		return false;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public Point getPos(){
		return posBar;
	}
	
	public void render(Graphics g){
		g.drawImage(bar, posBar.x, posBar.y, width, height, null);
		for(BuildingButtons b: buttons){
			b.render(g);
		}
		
	}
	
}
