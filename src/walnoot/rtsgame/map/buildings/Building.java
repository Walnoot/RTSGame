package walnoot.rtsgame.map.buildings;

import java.awt.*;

import walnoot.rtsgame.map.Map;


public abstract class Building {
	public abstract void render(Graphics g, Dimension screenSize, Point translation, Point position);
	public abstract int getID();
	public abstract void update(long timePassed, Point posMouse, int idNE, int idSE, int idSW , int idNW, int xPos, int Ypos);

}
