package walnoot.rtsgame.screen;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;

import walnoot.rtsgame.RTSComponent;

/**
 * Een Screen staat voor een deel van het spel, zoals het hoofdmenu, of game
 * scherm
 */
public abstract class Screen {
	public RTSComponent component;
	public static RTSFont font;
	
	public Screen(RTSComponent component){
		this.component = component;
		try{
			font = new RTSFont(ImageIO.read(this.getClass().getResource("/font.png")));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void setScreen(Screen screen){
		component.setScreen(screen);
	}
	
	/**
	 * Render method, tekent op het scherm
	 * 
	 * @param g Object waarmee je kan tekenen
	 */
	public abstract void render(Graphics g);
	
	/**
	 * Update Method
	 */
	public abstract void update();
	
	/** @param transparancy hoe transparant, max 1.0, min 0.0 */
	public void makeTransparant(Graphics g, float transparancy){
		Graphics2D g2d = (Graphics2D) g;
		Composite alphaComp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparancy);
		g2d.setComposite(alphaComp);
	}
	
	public Point getMouseLocation(){
		try{
			return new Point(
					(MouseInfo.getPointerInfo().getLocation().x - component.getLocationOnScreen().x) / RTSComponent.SCALE,
					(MouseInfo.getPointerInfo().getLocation().y - component.getLocationOnScreen().y) / RTSComponent.SCALE);
		}catch(Exception e){
			return new Point(0, 0);
		}
	}
	
	/** returns the size off the surface being rendered */
	public Dimension getScreenSize(){
		int x = component.getWidth() / RTSComponent.SCALE;
		int y = component.getHeight() / RTSComponent.SCALE;
		
		return new Dimension(x, y);
	}
	
	public int getWidth(){
		return component.getWidth() / RTSComponent.SCALE;
	}
	
	public int getHeight(){
		return component.getHeight() / RTSComponent.SCALE;
	}
	
	public void keyPressed(KeyEvent e){
	}
	
	public void keyReleased(KeyEvent e){
	}
	
	public void keyTyped(KeyEvent e){
	}
	
	public void mouseClicked(MouseEvent e){
	}
	
	public void mouseEntered(MouseEvent e){
	}
	
	public void mouseExited(MouseEvent e){
	}
	
	public void mousePressed(MouseEvent e){
	}
	
	public void mouseReleased(MouseEvent e){
	}
	
	public void mouseDragged(MouseEvent e){
	}
	
	public void mouseMoved(MouseEvent e){
	}
}
