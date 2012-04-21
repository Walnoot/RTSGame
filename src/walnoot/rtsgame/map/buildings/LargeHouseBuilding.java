package walnoot.rtsgame.map.buildings;

import java.awt.*;
import java.awt.image.*;

import javax.imageio.ImageIO;

public class LargeHouseBuilding extends Building {
	BufferedImage largeHouse;
	int ID = 3;
	
	public LargeHouseBuilding(){
		try{
			largeHouse = ImageIO.read(this.getClass().getResource("/largehousebuilding2.png"));
		}catch(Exception e){
			System.out.println("largehousebuilding picture not available?!");
		}
	}
	
	public void render(Graphics g, Dimension screenSize, Point translation, Point position){
		int x = getPointOnScreen(position).x;
		int y = getPointOnScreen(position).y;
		
		if((x + largeHouse.getWidth() + translation.x) < 0 || (y + largeHouse.getHeight() + translation.y) < 0) return;
		if((x + translation.x) > screenSize.width || (y + translation.y) > screenSize.height) return;
		
		g.setColor(Color.BLACK);
		
		g.drawImage(largeHouse, x - 16, y - 16, null);
	}
	
	public Point getPointOnScreen(Point coordinats){
		int x = (coordinats.x - coordinats.y) * -16;
		int y = (coordinats.x + coordinats.y) * 8;
		
		return new Point(x, y);
	}
	
	public int getID(){
		return ID;
	}
	
	public boolean isLarge(){
		return true;
	}
	
	public int getWidth(){
		return 2;
	}
	
	public int getHeight(){
		return 2;
	}
	
	public void update(Point posMouse, boolean mouseIsDown){
		
	}
	
}
