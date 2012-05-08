package walnoot.rtsgame.map.entities;

import java.awt.Color;
import java.awt.Graphics;

import walnoot.rtsgame.Util;
import walnoot.rtsgame.map.Map;
import walnoot.rtsgame.map.tribes.Tribe;
import walnoot.rtsgame.screen.Screen;

public class TestEntity extends MovingEntity {
	private String name;
	
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
	
	public void update(){
		super.update();
		
		//if(!isMoving()) moveRandomLocation();
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
