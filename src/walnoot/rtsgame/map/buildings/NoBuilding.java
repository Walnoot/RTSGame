package walnoot.rtsgame.map.buildings;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import walnoot.rtsgame.Images;

public class NoBuilding extends Building {
	int ID;
	
	public NoBuilding(int ID){
		this.ID = ID;
	}
	
	public void render(Graphics g, Dimension screenSize, Point translation, Point position){}
	
	public Point getPointOnScreen(Point coordinats){
		int x = (coordinats.x - coordinats.y) * -16;
		int y = (coordinats.x + coordinats.y) * 8;
		
		return new Point(x, y);
	}
	
	public void update(Point posMouse, boolean mouseIsDown){
	}
	
	public int getID(){
		return ID;
	}
	public boolean isLarge() {
		return false;
	}

	public int getWidth() {
		return 1;
	}

	public int getHeight() {
		return 1;
	}
}
