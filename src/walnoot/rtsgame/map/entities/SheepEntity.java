package walnoot.rtsgame.map.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import walnoot.rtsgame.Images;
import walnoot.rtsgame.InputHandler;
import walnoot.rtsgame.Util;
import walnoot.rtsgame.map.Map;
import walnoot.rtsgame.popups.Option;
import walnoot.rtsgame.popups.OptionsPopup;
import walnoot.rtsgame.screen.GameScreen;

public class SheepEntity extends MovingEntity {
	
	private int counter = 0;
	private BufferedImage sheep1;
	private BufferedImage sheep2;
	private BufferedImage sheep3;
	private BufferedImage sheep4;
	private BufferedImage sheep5;
	private BufferedImage sheep6;
	private BufferedImage currentSheep;
	private BufferedImage stillSheep;
	
	public static final int WALK_RANGE = 6, WALK_CHANGE = 5;

	public SheepEntity(Map map, int xPos, int yPos) {
		super(map, xPos, yPos, null);
		sheep1 = Images.sheep[0][0];
		sheep2 = Images.sheep[1][0];
		sheep3 = Images.sheep[2][0];
		sheep4 = Images.sheep[3][0];
		sheep5 = Images.sheep[4][0];
		sheep6 = Images.sheep[5][0];
		stillSheep = Images.sheep[6][0];
		currentSheep = sheep1;
	}

	protected double getTravelTime() {
		return 1000;
	}
	
	private BufferedImage getNextSheep(Image prevSheep){
		if (prevSheep == sheep1) return sheep2;
		else if (prevSheep == sheep2) return sheep3;
		else if (prevSheep == sheep3) return sheep4;
		else if (prevSheep == sheep4) return sheep5;
		else if (prevSheep == sheep5) return sheep6;
		else if (prevSheep == sheep6) return sheep1;
		return null;
	}
	
	public int getSelectedOption() {
		return -1;
	}
	
	public void update(){
		if(isMoving()) counter++;
		if(counter > 30){
			counter = 0;
			currentSheep = getNextSheep(currentSheep);
			
		}
		super.update();
		
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
			
			//screen.setPopup(new TextPopup(input, this, "ljhadfl;ewr", "daloshjrew"));
		}else{
			follow(entityClicked);
		}
		
		return false;
	}
	
	public void setSelectedOption(int indexSelected) {}

	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		if(isMoving())g.drawImage(Images.sheep[(counter/5)][0], getScreenX(), getScreenY(), null);
		else g.drawImage(stillSheep, getScreenX(), getScreenY(), null);
	}

	public int getMaxHealth() {
		return 5;
	}

	public String getName() {
		return "Sheep";
	}
}
