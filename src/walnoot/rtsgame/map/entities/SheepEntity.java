package walnoot.rtsgame.map.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import walnoot.rtsgame.Animation;
import walnoot.rtsgame.Images;
import walnoot.rtsgame.InputHandler;
import walnoot.rtsgame.Util;
import walnoot.rtsgame.map.Map;
import walnoot.rtsgame.map.tiles.Tile;
import walnoot.rtsgame.popups.Option;
import walnoot.rtsgame.popups.OptionsPopup;
import walnoot.rtsgame.screen.GameScreen;

public class SheepEntity extends MovingEntity {
	
	private int counter = 0;
	//private BufferedImage stillSheep;
	private Animation sheepAnimation;
	
	public static final int WALK_RANGE = 6, WALK_CHANGE = 0, TICKS_PER_SHEEP = 5;

	public SheepEntity(Map map, int xPos, int yPos) {
		super(map, xPos, yPos, null);
		sheepAnimation = new Animation(TICKS_PER_SHEEP);
		for(int i = 0; i < Images.sheep.length-1; i++){
			sheepAnimation.addScene(Images.sheep[i][0]);
		}
	}

	protected double getTravelTime() {
		return 1000;
	}
	
	
	public int getSelectedOption() {
		return -1;
	}
	
	public void update(){
		if(isMoving()) counter++;
		if(counter >= TICKS_PER_SHEEP*(Images.sheep.length-1)) counter = 0;
		super.update();
		sheepAnimation.update();		// 1 tick has passed...
		if(!isMoving() && Util.RANDOM.nextInt(1000) < WALK_CHANGE) moveRandomLocation(WALK_RANGE);
	}
	
	public boolean onRightClick(Entity entityClicked, GameScreen screen, InputHandler input){
		if(entityClicked == this){
			OptionsPopup popup = new OptionsPopup(input, this);
			Option option1 = new Option("getclosest"){
				public void onClick() {
					System.out.println(map.getClosestEntity(getxPos(), getyPos()).getName());
				}
			};
			popup.addOption(option1);
			popup.addOption(new Option("get closest moving entity"){
				public void onClick() {
					System.out.println(map.getClosestMovingEntity(getxPos(), getyPos()).getName());
				}
			});
			
			screen.setPopup(popup);
			
		}else{
			follow(entityClicked);
		}
		
		return false;
	}
	
	public void setSelectedOption(int indexSelected){}

	public void render(Graphics g){
		g.setColor(Color.BLACK);
		if(isMoving()) g.drawImage(sheepAnimation.getImage(), getScreenX(), getScreenY() - Tile.getHeight() / 2, null);
		else g.drawImage(Images.sheep[Images.sheep.length-1][0], getScreenX(), getScreenY() - Tile.getHeight() / 2, null);
	}

	public int getMaxHealth(){
		return 5;
	}

	public String getName(){
		return "Sheep";
	}
}
