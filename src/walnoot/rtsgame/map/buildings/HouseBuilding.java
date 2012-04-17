package walnoot.rtsgame.map.buildings;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import walnoot.rtsgame.*;
import walnoot.rtsgame.map.Map;

public class HouseBuilding extends Building {
	BufferedImage house;
	
	public HouseBuilding(){
		house = getImage(3, 0);
	}
	
	public BufferedImage getImage(int x, int y){
		return Images.terrain[x][y];
	}
	
	public void render(Graphics g, Dimension screenSize, Point translation, Point position){
		
		int x = getPointOnScreen(position).x;
		int y = getPointOnScreen(position).y;
		
		if((x + house.getWidth() + translation.x) < 0 || (y + house.getHeight() + translation.y) < 0) return;
		if((x + translation.x) > screenSize.width || (y + translation.y) > screenSize.height) return;
		
		g.setColor(Color.BLACK);
		g.drawImage(house, x, y, null);
	}
	
	public Point getPointOnScreen(Point coordinats){
		int x = (coordinats.x - coordinats.y) * -16;
		int y = (coordinats.x + coordinats.y) * 8;
		
		return new Point(x, y);
	}
	
	public void update(Point posMouse, boolean mouseIsDown){
		
	}
	
	public int getID(){
		return 2;
	}
	
}
