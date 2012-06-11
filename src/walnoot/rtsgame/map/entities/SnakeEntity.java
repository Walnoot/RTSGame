package walnoot.rtsgame.map.entities;

import java.awt.Color;
import java.awt.Graphics;

import walnoot.rtsgame.InputHandler;
import walnoot.rtsgame.Util;
import walnoot.rtsgame.map.Map;
import walnoot.rtsgame.popups.Option;
import walnoot.rtsgame.popups.OptionsPopup;
import walnoot.rtsgame.screen.GameScreen;
import walnoot.rtsgame.screen.Screen;

public class SnakeEntity extends MovingEntity {
	
	private boolean scared = false;
	private int ticksScared = 11;
	
	public static final int WALK_RANGE = 3, WALK_CHANGE_NORMAL = 3, MAX_TICKS_SCARED = 1000, WALK_CHANGE_SCARED = 20;

	public SnakeEntity(Map map, int xPos, int yPos) {
		super(map, xPos, yPos, null);
	}

	protected double getTravelTime() {
		return 100;
	}

	public int getSelectedOption() {
		return -1;
	}
	
	public void update(){
		if(ticksScared >= MAX_TICKS_SCARED)scared = false;
		if(scared) {
			ticksScared++;
		}
		
		
		super.update();
		if(scared)if(!isMoving() && Util.RANDOM.nextInt(1000) < WALK_CHANGE_SCARED) moveRandomLocation(WALK_RANGE);
		else if(!isMoving() && Util.RANDOM.nextInt(1000) < WALK_CHANGE_NORMAL) moveRandomLocation(WALK_RANGE);
		
	}
	
	public void onRightClick(GameScreen screen, InputHandler input){
		OptionsPopup popup = new OptionsPopup(input, this);
		Option option1 = new Option("boe!"){
			public void onClick() {
				scare();
			}
		};
		popup.addOption(option1);
		screen.setPopup(popup);
	}
	
	public void scare(){
		scared  = true;
		ticksScared = 0;
		System.out.println("boe!!!");
	}
	
	public void setSelectedOption(int indexSelected) {}

	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		Screen.font.drawLine(g, getName(), getScreenX(), getScreenY());
	}

	public int getMaxHealth() {
		return 5;
	}

	public String getName() {
		return "Snake";
	}

}
