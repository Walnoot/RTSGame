package walnoot.rtsgame.map.buildings;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import walnoot.rtsgame.map.Map;
public class NoBuilding extends Building{
	public void render(Graphics g, Dimension screenSize, Point translation,Point position) {}
	public void update(long timePassed, Point posMouse, int idNE, int idSE, int idSW , int idNW, int xPos,int Ypos) {}
	public int getID() {
		return 0;
	}
}
