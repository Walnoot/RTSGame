package walnoot.rtsgame.screen.menus;

import java.awt.Graphics;

import walnoot.rtsgame.RTSComponent;
import walnoot.rtsgame.screen.GameScreen;
import walnoot.rtsgame.screen.Screen;
import walnoot.rtsgame.screen.TitleScreen;

public class MainMenu extends MenuScreen{
	private Screen title;
	private float buttonTransparancy;
	
	private MenuButton startGame = new MenuButton("Start Game", 0, 100 , -1, -1, this);
	private MenuButton exitGame = new MenuButton("Exit Game", 0, 130 , -1, -1, this);
	
	public MainMenu(RTSComponent component, TitleScreen title) {
		super(component);
		this.title = title;
	}

	public void render(Graphics g){
		title.render(g);
		
		super.makeTransparant(g, buttonTransparancy);
		super.render(g);
		
	}
	
	protected MenuButton[] getButtons(){
		MenuButton[] result = {startGame, exitGame};
		
		return result;
	}
	
	public void update(double timePassed){
		super.update(timePassed);
		//title.update(timePassed);
		
		if(buttonTransparancy < 1.0f){
			buttonTransparancy += (float)timePassed / 1000;
		}
		if(buttonTransparancy > 1.0f){
			buttonTransparancy = 1.0f;
		}
	}
	
	public void buttonPressed(MenuButton menuButton) {
		if(menuButton.equals(startGame)) super.component.setScreen(new GameScreen(component));
		else if(menuButton.equals(exitGame)) component.stop();
	}
}
