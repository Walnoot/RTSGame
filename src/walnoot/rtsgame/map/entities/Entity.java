package walnoot.rtsgame.map.entities;

import java.awt.Graphics;

import walnoot.rtsgame.InputHandler;
import walnoot.rtsgame.map.Map;
import walnoot.rtsgame.map.tiles.Tile;
import walnoot.rtsgame.map.tribes.Tribe;
import walnoot.rtsgame.screen.GameScreen;

public abstract class Entity {
	protected final Map map;
	private boolean removed;
	public int xPos;
	public int yPos;
	protected int health;
	public Tribe tribe;
	
	public Entity(Map map, int xPos, int yPos, Tribe tribe){
		this.map = map;
		this.xPos = xPos;
		this.yPos = yPos;
		this.tribe = tribe;
		
		health = getMaxHealth();
	}
	
	public abstract void update();
	public abstract void render(Graphics g);
	public abstract int getMaxHealth();
	public abstract String getName();
	
	/**
	 * @param entityClicked Entity that is right-clicked
	 * @param screen
	 * @param input
	 * @return whether this Entity can move after onRightClick()
	 */
	public boolean onRightClick(Entity entityClicked, GameScreen screen, InputHandler input){
		return true;
	}
	public int getHealth(){
		return health;
	}
	
	public void damage(int damage){
		health -= damage;
	}
	
	public int getxPos(){
		return xPos;
	}
	
	public int getyPos(){
		return yPos;
	}
	
	public int getScreenX(){
		return (xPos - yPos) * (-Tile.WIDTH / 2);
	}
	
	public int getScreenY(){
		return (xPos + yPos) * (Tile.HEIGHT / 2);
	}
	
	public boolean isSolid(int x, int y){
		return false;
	}
	
	public Tribe getTribe(){
		return tribe;
	}
	
	public void setTribe(Tribe tribe){
		this.tribe = tribe;
	}
	
	public boolean isRemoved(){
		if(health <= 0) return true;
		return removed;
	}
	
	public void remove(){
		removed = true;
	}
}
