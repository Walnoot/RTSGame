package walnoot.rtsgame.screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import walnoot.rtsgame.RTSComponent;
import walnoot.rtsgame.map.Map;
import walnoot.rtsgame.map.entities.DeerEntity;
import walnoot.rtsgame.map.entities.TestEntity;
import walnoot.rtsgame.map.tribes.Tribe;

public class GameScreen extends Screen {
	private Map map;
	private double translationX, translationY;
	private boolean up, down, left, right;
	private Point mousePos = new Point(0, 0);
	boolean mouseIsDown = false;
	boolean mousePressed = false;
	
	private Point mouseCoordinats = new Point();
	private TestEntity player;
	
	private Tribe tribe;
	
	public GameScreen(RTSComponent component){
		super(component);
		
		map = new Map(256);
		
		int goodYPos;
		
		for(int i = 4;; i++){
			if(!map.getTile(4, i).isSolid()){
				player = new TestEntity(map, 4, i, null);
				goodYPos = i;
				break;
			}
		}
		map.addEntity(player);
		
		tribe = new Tribe("My Tribe", Color.BLUE);
		player.setTribe(tribe);
		
		map.addEntity(new DeerEntity(map, 4, goodYPos)); //voor de test, later weghalen
		
		translationX = -player.getScreenX();
		translationY = -player.getScreenY();
	}
	
	public void render(Graphics g){
		map.render(g, new Point((int) translationX, (int) translationY), super.getScreenSize());
		
		g.setColor(Color.WHITE);
		font.drawBoldLine(g, mouseCoordinats.x + ":" + mouseCoordinats.y, 50, 10, Color.BLACK);
	}
	
	public void update(){
		map.update(mousePos, mousePressed, (int) translationX, (int) translationY);
		
		if(up) translationY += 5.0;
		if(down) translationY -= 5.0;
		if(left) translationX += 5.0;
		if(right) translationX -= 5.0;
	}
	
	public void mouseReleased(MouseEvent e){
		if(!(map.selectBar.isInBar(mousePos))){
			player.moveTo(mouseCoordinats);
		}
		mousePressed = false;
	}
	
	public void mouseMoved(MouseEvent e){
		int mouseX = (int) ((e.getX() - 32) / RTSComponent.SCALE - translationX);
		int mouseY = (int) (e.getY() / RTSComponent.SCALE - translationY);
		
		int x = (int) ((mouseY / 16.0) - (mouseX / 32.0));
		int y = (int) ((mouseY / 16.0) + (mouseX / 32.0));
		mousePos = e.getPoint();
		mouseCoordinats.setLocation(x, y);
	}
	
	public void mouseDragged(MouseEvent e){
		int mouseX = (int) ((e.getX() - 32) / RTSComponent.SCALE - translationX);
		int mouseY = (int) (e.getY() / RTSComponent.SCALE - translationY);
		
		int x = (int) ((mouseY / 16.0) - (mouseX / 32.0));
		int y = (int) ((mouseY / 16.0) + (mouseX / 32.0));
		mousePos = e.getPoint();
		mouseCoordinats.setLocation(x, y);
	}
	
	public void mousePressed(MouseEvent e){
		mousePressed = true;
		mousePos = e.getPoint();
	}
	
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_UP) up = true;
		if(e.getKeyCode() == KeyEvent.VK_DOWN) down = true;
		if(e.getKeyCode() == KeyEvent.VK_LEFT) left = true;
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) right = true;
	}
	
	public void keyReleased(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_UP) up = false;
		if(e.getKeyCode() == KeyEvent.VK_DOWN) down = false;
		if(e.getKeyCode() == KeyEvent.VK_LEFT) left = false;
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) right = false;
	}
}
