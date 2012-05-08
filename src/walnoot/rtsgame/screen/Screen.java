package walnoot.rtsgame.screen;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import walnoot.rtsgame.Images;
import walnoot.rtsgame.InputHandler;
import walnoot.rtsgame.RTSComponent;

/**
 * Een Screen staat voor een deel van het spel, zoals het hoofdmenu, of game
 * scherm
 */
public abstract class Screen {
	public RTSComponent component;
	public static RTSFont font;
	protected InputHandler input;
	
	public Screen(RTSComponent component, InputHandler input){
		this.component = component;
		this.input = input;
		
		if(font != null) return;
		font = new RTSFont(Images.load("/font.png"));
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
		return new Point(input.getMouseX(), input.getMouseY());
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
}
