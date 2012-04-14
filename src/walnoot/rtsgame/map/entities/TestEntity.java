package walnoot.rtsgame.map.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import walnoot.rtsgame.map.Map;
import walnoot.rtsgame.map.tribes.NameGenerator;
import walnoot.rtsgame.map.tribes.Tribe;
import walnoot.rtsgame.screen.Screen;

public class TestEntity extends MovingEntity{
	private Random random;
	private String name;
	
	public TestEntity(Map map, int xPos, int yPos, Tribe tribe){
		super(map, xPos, yPos, tribe);
		
		random = new Random();
		name = new NameGenerator().getRandomName();
		
		//moveRandomLocation();
	}
	
	public void render(Graphics g){
		if(getTribe() != null) g.setColor(getTribe().getColor());
		else g.setColor(Color.BLACK);
		g.fillRect(getPointOnScreen().x + 14, getPointOnScreen().y + 6, 4, 4);
		
		g.setColor(Color.WHITE);
		Screen.font.drawBoldLine(g, xPos + ":" + yPos, getPointOnScreen().x, getPointOnScreen().y - 8, Color.BLACK);
	}
	
	public void update(){
		super.update();
		
		//if(!isMoving()) moveRandomLocation();
	}
	
	public void moveRandomLocation(){
		int x, y;
		
		do{
			x = random.nextInt(map.getWidth());
			y = random.nextInt(map.getHeigth());
		}while(map.isSolid(x, y));
		
		xPos = x;
		yPos = y;
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
