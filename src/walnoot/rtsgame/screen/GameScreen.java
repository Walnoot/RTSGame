package walnoot.rtsgame.screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import walnoot.rtsgame.InputHandler;
import walnoot.rtsgame.RTSComponent;
import walnoot.rtsgame.Util;
import walnoot.rtsgame.map.Map;
import walnoot.rtsgame.map.entities.DeerEntity;
import walnoot.rtsgame.map.entities.Entity;
import walnoot.rtsgame.map.entities.MovingEntity;
import walnoot.rtsgame.map.entities.TestEntity;
import walnoot.rtsgame.map.structures.TentStructure;
import walnoot.rtsgame.map.tribes.Tribe;

public class GameScreen extends Screen {
	private Map map;
	private int translationX, translationY;
	
	private Entity selectedEntity;
	
	private Tribe tribe;
	
	public GameScreen(RTSComponent component, InputHandler input){
		super(component, input);
		
		map = new Map(256);
		
		int goodYPos;
		
		for(int i = 4;; i++){
			if(!map.getTile(4, i).isSolid()){
				selectedEntity = new TestEntity(map, 4, i, null);
				goodYPos = i;
				break;
			}
		}
		map.addEntity(selectedEntity);
		
		tribe = new Tribe("My Tribe", Color.BLUE);
		selectedEntity.setTribe(tribe);
		
		map.addEntity(new DeerEntity(map, 4, goodYPos)); //voor de test, later weghalen
		map.addEntity(new TentStructure(map, 4, goodYPos + 10, tribe)); //voor de test, later weghalen
		
		translationX = -selectedEntity.getScreenX();
		translationY = -selectedEntity.getScreenY();
	}
	
	public void render(Graphics g){
		map.render(g, new Point((int) translationX, (int) translationY), super.getScreenSize());
		
		int x = Util.getMapX(input.getMouseX() - translationX, input.getMouseY() - translationY);
		int y = Util.getMapY(input.getMouseX() - translationX, input.getMouseY() - translationY);
		
		g.setColor(Color.WHITE);
		font.drawBoldLine(g, x + ":" + y, 20, 20, Color.BLACK);
		
		if(selectedEntity != null){
			font.drawBoldLine(g, selectedEntity.getName(), 20, getHeight() - 40, Color.BLACK);
			font.drawBoldLine(g, "Health: " + selectedEntity.getHealth(), 20, getHeight() - 30, Color.BLACK);
		}
	}
	
	public void update(){
		map.update((int) translationX, (int) translationY);
		
		if(input.up.isPressed()) translationY += 5;
		if(input.down.isPressed()) translationY -= 5;
		if(input.left.isPressed()) translationX += 5;
		if(input.right.isPressed()) translationX -= 5;
		
		if(input.LMBTapped()){
			selectedEntity = map.getEntity(getMapX(), getMapY());
		}
		
		if(selectedEntity instanceof MovingEntity){
			if(input.RMBTapped()){
				((MovingEntity) selectedEntity).moveTo(new Point(getMapX(), getMapY()));
			}
		}
	}
	
	private int getMapX(){
		return Util.getMapX(input.getMouseX() - translationX, input.getMouseY() - translationY);
	}
	
	private int getMapY(){
		return Util.getMapY(input.getMouseX() - translationX, input.getMouseY() - translationY);
	}
}
