package walnoot.rtsgame.map.buildings.buttons;

import java.awt.*;
import java.awt.image.*;

import javax.imageio.ImageIO;

import walnoot.rtsgame.RTSComponent;
import walnoot.rtsgame.map.*;
/** een BuildingButton is een knopje voor op de SelectBar.
 * een BuildingButton genereerd de bijbehorende Building
 * bevat:			:
 * -HouseButton
 * -LargeHouseButton
 * -RoadButton
 * -TrashButton*/
public abstract class BuildingButtons {
	BufferedImage Button;
	
	
	public abstract void render(Graphics g);
	public abstract void update(Point mousePos, boolean mouseIsDown, int translationX, int translationY, boolean isOnBar);
	public abstract void isSelected();
	public abstract void isDeselected();
	

	public Point screenToMap(int translationX, int translationY, Point e){
		int mouseX = (((int)e.getX()) - 32) / RTSComponent.SCALE - translationX;
		int mouseY = ((int)e.getY()) / RTSComponent.SCALE - translationY;
		
		int x = (int) ((mouseY / 16.0) - (mouseX / 32.0));
		int y = (int) ((mouseY / 16.0) + (mouseX / 32.0));
		return new Point(x,y);
		
	}
	
	
}
