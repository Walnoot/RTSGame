package walnoot.rtsgame.map.buildings;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import walnoot.rtsgame.map.Map;

public abstract class Building {
	public static final int MAX_SIZE = 4; //maximale grootte van de Building
	
	public abstract void render(Graphics g, Dimension screenSize, Point translation, Point position);
	
	public abstract int getWidth();
	public abstract int getHeight();
	public abstract String getName();
	
	public abstract void update(Map map);
}
