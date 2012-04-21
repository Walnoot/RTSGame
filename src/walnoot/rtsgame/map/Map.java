package walnoot.rtsgame.map;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedHashSet;
import java.util.Set;

import walnoot.rtsgame.map.buildings.*;
import walnoot.rtsgame.map.entities.*;
import walnoot.rtsgame.map.tiles.*;

public class Map {
	private Tile[][] surface;
	private Set<Entity> entities = new LinkedHashSet<Entity>();
	public Building[][] buildings;
	public SelectBar selectBar = new SelectBar(this);
	
	public Map(int mapSize){
		surface = new Tile[mapSize][mapSize];
		buildings = new Building[mapSize][mapSize];
		generateMap();
	}
	
	public void update(Point mousePos, boolean mouseIsDown, int translationX, int translationY){
		for(Entity e: entities){
			e.update();
		}
		
		selectBar.update(mousePos, mouseIsDown, translationX, translationY);
		for(int x = 1; x < getWidth() - 1; x++){
			for(int y = 1; y < getWidth() - 1; y++){
				if(getBuilding(x, y) != null){
					buildings[x][y].update(mousePos, mouseIsDown);
				}
			}
		}
	}
	
	public void generateMap(){
		//long then = System.currentTimeMillis();
		
		for(int x = 0; x < getWidth(); x++){
			for(int y = 0; y < getWidth(); y++){
				float noise = PerlinNoise2D.perlinNoise(x, y, 0.3f, 32f, 4);
				//double noise = SimplexNoise.noise(x / 64f, y / 64f);
				
				if(noise > 0) surface[x][y] = Tile.grass1;
				else if(noise > -0.2f) surface[x][y] = Tile.sand1;
				else surface[x][y] = Tile.water1;
			}
		}
		for(int x = 0; x < getWidth(); x++){
			for(int y = 0; y < getWidth(); y++){
				buildings[x][y] = null;
			}
		}
		
		//System.out.println(System.currentTimeMillis() - then);
	}
	
	public void render(Graphics g, Point translation, Dimension screenSize){
		g.translate(translation.x, translation.y);
		
		for(int x = 0; x < getWidth(); x++){
			for(int y = 0; y < getHeigth(); y++){
				getTile(x, y).render(g, screenSize, translation, new Point(x, y));
			}
		}
		for(int x = 0; x < getWidth(); x++){
			for(int y = 0; y < getHeigth(); y++){
				if(getBuilding(x, y) != null){
					getBuilding(x, y).render(g, screenSize, translation, new Point(x, y));
				}
				
			}
		}
		
		for(Entity e: entities){
			e.render(g);
		}
		
		g.translate(-translation.x, -translation.y);
		selectBar.render(g);
	}
	
	public boolean isSolid(Point pos){
		return isSolid(pos.x, pos.y);
	}
	
	public boolean isSolid(int x, int y){
		if(x < 0 || y < 0 || x >= surface.length || y >= surface.length) return true;
		if(surface[x][y].isSolid()) return true;
		
		Entity e = getEntity(x, y);
		if(e != null && e.isSolid(x, y)) return true;
		
		return false;
	}
	
	public Entity getEntity(int x, int y){
		for(Entity e: entities){
			if(e.getxPos() == x && e.getyPos() == y) return e;
		}
		return null;
	}
	
	public void deleteBuilding(int xPos, int yPos){
		int width, height;
		boolean largeBuilding = false;
		if(isOnMap(xPos,yPos)&& buildings[xPos][yPos]!=null){
			int ID = buildings[xPos][yPos].getID();
			for(;;){
				if(ID >= 10 && ID <= 24)largeBuilding = true;
				if(buildings[xPos-1][yPos]!=null && buildings[xPos-1][yPos].getID() == ID + 4){								// zoekt hoe groot het gebouw is. Zie ID's grote gebouwen.png	
					ID+=4;																									// zet de x- en ycoordinaten zo ver mogelijk van het gebouw af.
					xPos-=1;
				}else if(buildings[xPos][yPos-1]!=null && buildings[xPos][yPos-1].getID() == ID + 1){
					ID+=1;
					yPos-=1;
				}else{
					if(largeBuilding){
						width = (int) (buildings[xPos][yPos].getID() - 8 ) % 4;
						System.out.println("width: " + width);
						height = (int) (buildings[xPos][yPos].getID()-8 - width )/3 + 1;
						System.out.println("height: " + height);
					}else{
						
						if(buildings[xPos][yPos].isLarge()){
							width = buildings[xPos][yPos].getWidth();
							height = buildings[xPos][yPos].getHeight();
							xPos-=width-1;
							yPos-=height-1;
						}else{
							width = 1;
							height = 1;
						}
					}
					break;
				}
			}
			for(int x = 0; x < height; x++){
				for(int y = 0; y < width; y++){
					buildings[xPos + x][yPos + y]=null;
				}
			}
		}
	}
	
	public boolean isOnMap(int x, int y){
		if(x <= getHeigth() && x >= 0 && y >= 0 && y < getWidth()){
			return true;
		}
		return false;
	}
	
	public void addBuilding(Building b, int x, int y){
		if(x > 0 && x < getHeigth() && y > 0 && y < getWidth()){
			if(!surface[x][y].isSolid() && buildings[x][y] == null){
				buildings[x][y] = b;
			}
		}
	}
	
	public Tile getTile(int x, int y){
		try{
			return surface[x][y];
		}catch(ArrayIndexOutOfBoundsException e){
			return null;
		}
	}
	
	public Building getBuilding(int x, int y){
		try{
			return (buildings[x][y]);
		}catch(Exception e){
			return null;
		}
	}
	
	public int getWidth(){
		return surface.length;
	}
	
	public int getHeigth(){
		return surface[0].length;
	}
	
	public void addEntity(Entity u){
		entities.add(u);
	}
	
	public void removeEntity(Entity u){
		entities.remove(u);
	}
}
