package walnoot.rtsgame.map.tiles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import walnoot.rtsgame.Images;

public class Tile {
	public static final int WIDTH = 32, HEIGHT = 16;
	
	public static Tile[] tiles = new Tile[256];
	
	public static Tile grass1 = new Tile(1, false);
	public static Tile grass2 = new Tile(0, false);
	public static Tile sand1 = new Tile(17, false);
	public static Tile water1 = new Tile(2, true);
	
	protected final boolean solid;
	protected final byte id;
	
	protected Tile(int id, boolean solid){
		this.id = (byte) id;
		this.solid = solid;
		
		if(tiles[id] != null) throw new IllegalArgumentException("dublicate tile id!");
		tiles[id] = this;
	}
	
	public void render(Graphics g, Dimension screenSize, Point translation, Point position){
		BufferedImage image = getImage();
		
		int x = getPointOnScreen(position).x;
		int y = getPointOnScreen(position).y;
		
		if((x + image.getWidth() + translation.x) < 0 || (y + image.getHeight() + translation.y) < 0) return;
		if((x + translation.x) > screenSize.width || (y + translation.y) > screenSize.height) return;
		
		g.setColor(Color.BLACK);
		g.drawImage(image, x, y, null);
		
		/*g.drawLine(x, y + 8, x + 16, y);
		g.drawLine(x + 16, y, x, y - 8);*/
		
		//Screen.font.drawBoldLine(g, String.format("%d:%d", position.x, position.y), x + 8, y, Color.WHITE);
		
		//vanaf hier worden kustlijnen gerendert
		
		/*if(id == water1.id) return;
		
		Direction[] directions = {Direction.SOUTH_EAST, Direction.SOUTH_WEST, Direction.NORTH_WEST, Direction.NORTH_EAST};
		
		for(int i = 0; i < directions.length; i++){
			Direction direction = directions[i];
			
			if(direction.getTile(map, position) != null && direction.getTile(map, position).id == water1.id){
				g.drawImage(getImage(2, 1 + i), x, y, null);
			}
		}
		
		Direction[] corners = {Direction.SOUTH, Direction.NORTH, Direction.EAST, Direction.SOUTH};
		
		for(int i = 0; i < corners.length; i++){
			Direction direction = corners[i];
			
			if(direction.getTile(map, position) == null || direction.getTile(map, position).id != water1.id) continue;
			
			try{
				if(directions[i].getTile(map, position).id != water1.id && directions[(i + 1) % 4].getTile(map, position).id != water1.id ){
					g.drawImage(getImage(3, 1 + i), x, y, null);
				}
			}catch(NullPointerException e){}
		}*/
	}
	
	public Point getPointOnScreen(Point coordinats){
		int x = (coordinats.x - coordinats.y) * (-Tile.WIDTH / 2);
		int y = (coordinats.x + coordinats.y) * (Tile.HEIGHT / 2);
		
		return new Point(x, y);
	}
	
	public BufferedImage getImage(){
		return getImage(id);
	}
	
	public BufferedImage getImage(int id){
		return getImage(id % 16, id / 16);
	}
	
	public BufferedImage getImage(int x, int y){
		return Images.terrain[x][y];
	}
	
	public boolean isSolid(){
		return solid;
	}
}