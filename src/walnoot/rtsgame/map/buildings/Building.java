package walnoot.rtsgame.map.buildings;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

/** een Building is een gebouw ter grootte van 1 tile */

public abstract class Building {
	public abstract void render(Graphics g, Dimension screenSize, Point translation, Point position);
	
	public abstract int getID();
	
	public abstract void update(Point posMouse, boolean mouseIsDown);
	
}
