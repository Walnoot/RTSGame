package walnoot.rtsgame.map.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import walnoot.rtsgame.InputHandler;
import walnoot.rtsgame.Util;
import walnoot.rtsgame.map.Map;
import walnoot.rtsgame.map.tribes.Tribe;
import walnoot.rtsgame.popups.TextPopup;
import walnoot.rtsgame.screen.GameScreen;
import walnoot.rtsgame.screen.Screen;

public class TestEntity extends MovingEntity {
	private String name;
	private ArrayList<ItemEntity> inventory = new ArrayList<ItemEntity>();
	
	public TestEntity(Map map, int xPos, int yPos, Tribe tribe){
		super(map, xPos, yPos, tribe);
		
		name = Util.NAME_GEN.getRandomName();
		
		//moveRandomLocation();
	}
	
	public void render(Graphics g){
		if(getTribe() != null) g.setColor(getTribe().getColor());
		else g.setColor(Color.BLACK);
		g.fillRect(getScreenX() + 14, getScreenY() + 6, 4, 4);
		
		g.setColor(Color.WHITE);
		Screen.font.drawBoldLine(g, xPos + ":" + yPos, getScreenX(), getScreenY() - 8, Color.BLACK);
	}
	
	protected void onStopMoving(){
		Entity e = map.getEntity(xPos, yPos);
		
		if(e instanceof ItemEntity){
			ItemEntity item = (ItemEntity) e;
			inventory.add(item);
			
			map.removeEntity(e);
		}
	}
	
	public void onRightClick(GameScreen screen, InputHandler input){
		screen.setPopup(new TextPopup(input, this, "Dit is een zin", "Dit ook", "Heel erg lange zinnen"));
	}
	
	protected double getTravelTime(){
		return 100;
	}
	
	public int getMaxHealth(){
		return 10;
	}
	
	public String getName(){
		return name;
	}
}
