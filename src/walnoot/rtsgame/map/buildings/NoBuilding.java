package walnoot.rtsgame.map.buildings;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import walnoot.rtsgame.map.Map;
public class NoBuilding extends Building{
	int ID;
	public NoBuilding(int ID){
		this.ID = ID;
	}
	public void render(Graphics g, Dimension screenSize, Point translation,Point position) {}
	public void update( Point posMouse, boolean mouseIsDown) {}
	public int getID() {
		return ID;
	}
}
