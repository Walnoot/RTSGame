package walnoot.rtsgame.screen;

import java.awt.Color;
import java.awt.Dimension;
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
import walnoot.rtsgame.popups.Popup;

public class GameScreen extends Screen {
	private Map map;
	private int translationX, translationY;
	private Popup popup = null;
	
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
		map.addEntity(new TentStructure(map, 4, goodYPos + 1, tribe)); //voor de test, later weghalen
		
		translationX = -selectedEntity.getScreenX();
		translationY = -selectedEntity.getScreenY();
	}
	
	public void render(Graphics g){
		Point translation = new Point((int) translationX, (int) translationY);
		
		map.render(g, translation, new Dimension(getWidth(), getHeight()));
		
		g.translate(translation.x, translation.y);
		if(popup != null) popup.render(g);
		g.translate(-translation.x, -translation.y);
		
		int x = getMapX();
		int y = getMapY();
		
		g.setColor(Color.WHITE);
		font.drawBoldLine(g, x + ":" + y, 20, 20, Color.BLACK);
		
		if(selectedEntity != null){
			font.drawBoldLine(g, selectedEntity.getName(), 20, getHeight() - 40, Color.BLACK);
			font.drawBoldLine(g, "Health: " + selectedEntity.getHealth(), 20, getHeight() - 30, Color.BLACK);
		}
	}
	
	public void update(){
		map.update((int) Math.floor(translationX), (int) Math.floor(translationY));
		
		if(input.up.isPressed()) translationY += 5;
		if(input.down.isPressed()) translationY -= 5;
		if(input.left.isPressed()) translationX += 5;
		if(input.right.isPressed()) translationX -= 5;
		
		if(input.LMBTapped()){
			selectedEntity = map.getEntity(getMapX(), getMapY());
		}
		
		if(popup != null){
			popup.update();
			if(popup.getOwner() != selectedEntity) popup = null;
		}
		if(input.RMBTapped()){
			if(selectedEntity == null) return;
			
			if(getMapX() == selectedEntity.getxPos() && getMapY() == selectedEntity.getyPos()){
				selectedEntity.onRightClick(this, input);
			}else if(selectedEntity instanceof MovingEntity){
				((MovingEntity) selectedEntity).moveTo(new Point(getMapX(), getMapY()));
			}
		}
	}
	
	public void setPopup(Popup popup){
		this.popup = popup;
	}

	public void removePopup(){
		popup = null;
	}
	
	private int getMapX(){
		return Util.getMapX(input.getMouseX() - translationX, input.getMouseY() - translationY);
	}
	
	private int getMapY(){
		return Util.getMapY(input.getMouseX() - translationX, input.getMouseY() - translationY);
	}
}
