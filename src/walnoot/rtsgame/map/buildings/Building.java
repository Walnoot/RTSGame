package walnoot.rtsgame.map.buildings;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

/** een Building is een gebouw ter grootte van 1 tile */

public abstract class Building {
	public abstract void render(Graphics g, Dimension screenSize, Point translation, Point position);
	
	public abstract int getID();
	
	public abstract boolean isLarge();
	public abstract int getWidth();
	public abstract int getHeight();
	
	public abstract void update(Point posMouse, boolean mouseIsDown);
	
}
