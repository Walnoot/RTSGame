package walnoot.rtsgame.map.structures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import walnoot.rtsgame.Images;
import walnoot.rtsgame.map.Map;
import walnoot.rtsgame.map.tiles.Tile;
import walnoot.rtsgame.map.tribes.Tribe;

public abstract class BasicStructure extends Structure {
	private BufferedImage image;
	
	public BasicStructure(Map map, int xPos, int yPos, Tribe tribe, int textureX, int textureY){
		super(map, xPos, yPos, tribe);
		
		loadImage(textureX, textureY);
	}
	
	public void render(Graphics g){
		g.drawImage(image, getScreenX() - (Tile.WIDTH / 2) * (getSize() - 1), getScreenY() - (getHeadSpace() * Tile.HEIGHT) + (Tile.HEIGHT / 2) * (getSize() - 1), null);
	}
	
	private void loadImage(int textureX, int textureY){
		int x = textureX * Tile.WIDTH;
		int y = textureY * Tile.HEIGHT;
		
		int width = getSize() * Tile.WIDTH;
		int height = (getSize() + getHeadSpace()) * Tile.HEIGHT;
		
		image = Images.structures.getSubimage(x, y, width, height);
	}
	
	protected abstract int getHeadSpace();
}
