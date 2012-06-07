package walnoot.rtsgame.map;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import walnoot.rtsgame.map.entities.Entity;
import walnoot.rtsgame.map.structures.Structure;
import walnoot.rtsgame.map.tiles.Tile;

public class Map {
	private Tile[][] surface;
	private List<Entity> entities = new ArrayList<Entity>();
	
	private static final Comparator<Entity> spriteSorter = new Comparator<Entity>() {
		public int compare(Entity e0, Entity e1){
			int y0 = e0.getxPos() + e0.getyPos(); //aprox. screen y coordinate of e0
			int y1 = e1.getxPos() + e1.getyPos(); //aprox. screen y coordinate of e1
			
			if(y1 < y0)
				return +1;
			if(y1 > y0)
				return -1;
			return 0;
		}
		
	};
	
	public Map(int mapSize){
		surface = new Tile[mapSize][mapSize];
		generateMap();
	}
	
	public void update(int translationX, int translationY){
		for(Entity e: entities){
			e.update();
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
		
		//System.out.println(System.currentTimeMillis() - then);
	}
	
	public void render(Graphics g, Point translation, Dimension screenSize){
		g.translate(translation.x, translation.y);
		
		for(int x = 0; x < getWidth(); x++){
			for(int y = 0; y < getLength(); y++){
				getTile(x, y).render(g, screenSize, translation, new Point(x, y));
			}
		}
		
		Collections.sort(entities, spriteSorter);
		
		for(Entity e: entities){
			e.render(g);
		}
		
		g.translate(-translation.x, -translation.y);
	}
	
	public boolean isSolid(Point pos){
		return isSolid(pos.x, pos.y);
	}
	
	public boolean isSolid(int x, int y){
		if(x < 0 || y < 0 || x >= surface.length || y >= surface.length) return true; //buiten de map
		if(surface[x][y].isSolid()) return true;
		
		for(Entity e: entities){
			if(e.isSolid(x, y)) return true;
		}
		
		return false;
	}
	
	public Entity getEntity(int x, int y){
		for(Entity e: entities){
			if(e instanceof Structure){
				Structure structure = (Structure) e;
				
				int dx = x - structure.getxPos();
				int dy = y - structure.getyPos();
				
				if(dx >= 0 && dy >= 0){
					if(dx < structure.getSize() && dy < structure.getSize()) return structure;
				}
			}else{
				if(e.getxPos() == x && e.getyPos() == y) return e;
			}
		}
		return null;
	}
	
	public boolean isOnMap(int x, int y){
		if(x <= getLength() && x >= 0 && y >= 0 && y < getWidth()){
			return true;
		}
		return false;
	}
	
	public Tile getTile(int x, int y){
		try{
			return surface[x][y];
		}catch(ArrayIndexOutOfBoundsException e){
			return null;
		}
	}
	
	public int getWidth(){
		return surface.length;
	}
	
	public int getLength(){
		return surface[0].length;
	}
	
	public void addEntity(Entity u){
		if(getEntity(u.xPos, u.yPos)== null){

			entities.add(u);
		}
	}
	
	public void changeTile(int xPos, int yPos, Tile t){
		surface[xPos][yPos] = t;
	}
	
	public void removeEntity(Entity u){
		entities.remove(u);
	}
}
