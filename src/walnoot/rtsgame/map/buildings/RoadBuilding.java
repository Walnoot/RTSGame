package walnoot.rtsgame.map.buildings;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import walnoot.rtsgame.Images;
import walnoot.rtsgame.map.Map;

public class RoadBuilding extends Building {
	BufferedImage road;
	int xPos, yPos;
	
	public RoadBuilding(){
		road = getImage(4, 0);
	}
	
	public BufferedImage getImage(int x, int y){
		return Images.terrain[x][y];
	}
	
	public void render(Graphics g, Dimension screenSize, Point translation, Point position){
		int x = getPointOnScreen(position).x;
		int y = getPointOnScreen(position).y;
		
		if((x + road.getWidth() + translation.x) < 0 || (y + road.getHeight() + translation.y) < 0) return;
		if((x + translation.x) > screenSize.width || (y + translation.y) > screenSize.height) return;
		
		g.setColor(Color.BLACK);
		
		g.drawImage(road, x, y, null);
	}
	
	public Point getPointOnScreen(Point coordinats){
		int x = (coordinats.x - coordinats.y) * -16;
		int y = (coordinats.x + coordinats.y) * 8;
		
		return new Point(x, y);
	}
	
	public void update(Map map){
		/*int roadVersion = 1;
		int idNE;
		if(map.buildings[xPos - 1][yPos] != null) idNE = map.buildings[xPos - 1][yPos].getID();
		else idNE = 0;
		int idNW;
		if(map.buildings[xPos][yPos + 1] != null) idNW = map.buildings[xPos][yPos + 1].getID();
		else idNW = 0;
		int idSW;
		if(map.buildings[xPos + 1][yPos] != null) idSW = map.buildings[xPos + 1][yPos].getID();
		else idSW = 0;
		int idSE;
		if(map.buildings[xPos][yPos - 1] != null) idSE = map.buildings[xPos][yPos - 1].getID();
		else idSE = 0;
		boolean NEIsRoad;
		boolean SEIsRoad;
		boolean SWIsRoad;
		boolean NWIsRoad;
		boolean houseAv;
		
		if(idNE == 1){
			NEIsRoad = true;
			houseAv = false;
		}else if(idNE == 2){
			houseAv = true;
			NEIsRoad = false;
		}else{
			houseAv = false;
			NEIsRoad = false;
		}
		if(idNW == 1) NWIsRoad = true;
		else NWIsRoad = false;
		if(idSW == 1) SWIsRoad = true;
		else SWIsRoad = false;
		if(idSE == 1) SEIsRoad = true;
		else SEIsRoad = false;
		//kijkt welke soort weg ie moet renderen.
		if(NEIsRoad || houseAv || idNE == 3){
			if(NWIsRoad){
				if(SWIsRoad){
					if(SEIsRoad){
						roadVersion = 1;
					}else{
						roadVersion = 2;
					}
				}else{
					if(SEIsRoad){
						roadVersion = 11;
					}else{
						roadVersion = 8;
					}
				}
			}else{
				if(SWIsRoad){
					if(SEIsRoad){
						roadVersion = 9;
					}else{
						roadVersion = 3;
					}
				}else{
					if(SEIsRoad){
						roadVersion = 4;
					}else{
						roadVersion = 1;
					}
				}
			}
		}else{
			if(NWIsRoad){
				if(SWIsRoad){
					if(SEIsRoad){
						roadVersion = 10;
					}else{
						roadVersion = 6;
					}
				}else{
					if(SEIsRoad){
						roadVersion = 7;
					}else{
						roadVersion = 1;
					}
				}
			}else{
				if(SWIsRoad){
					if(SEIsRoad){
						roadVersion = 5;
					}else{
						roadVersion = 1;
					}
				}else{
					if(SEIsRoad){
						roadVersion = 1;
					}else{
						roadVersion = 1;
					}
				}
			}
		}
		road = Images.terrain[4][roadVersion - 1];*/
	}
	
	public int getWidth() {
		return 1;
	}

	public int getHeight() {
		return 1;
	}
	
	public String getName(){
		return "Road";
	}
}
