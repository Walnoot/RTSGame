package walnoot.rtsgame;

import java.util.Random;

import walnoot.rtsgame.map.tiles.Tile;

/**
 * Class met handige static methods en waarden
 */
public class Util {
	public static final Random RANDOM = new Random();
	public static final NameGenerator NAME_GEN = new NameGenerator();
	
	public static int getScreenX(int mapX, int mapY){
		return (mapX - mapY) * (-Tile.WIDTH / 2);
	}
	
	public static int getScreenY(int mapX, int mapY){
		return (mapX + mapY) * (Tile.HEIGHT / 2);
	}
	
	public static int getMapX(int screenX, int screenY){
		return (int) Math.floor((screenY / 16.0) - ((screenX - Tile.WIDTH / 2) / 32.0));
	}
	
	public static int getMapY(int screenX, int screenY){
		return (int) Math.floor((screenY / 16.0) + ((screenX - Tile.WIDTH / 2) / 32.0));
	}
}	
	