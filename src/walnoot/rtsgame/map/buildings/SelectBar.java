package walnoot.rtsgame.map.buildings;

import java.awt.Graphics;
import java.util.ArrayList;

import walnoot.rtsgame.InputHandler;
import walnoot.rtsgame.screen.Screen;

public class SelectBar {
	private ArrayList<Building> buildings = new ArrayList<Building>();
	private int index = -1; //if -1, nothing is selected
	
	public SelectBar(){
		buildings.add(new HouseBuilding());
		buildings.add(new LargeHouseBuilding());
		buildings.add(new RoadBuilding());
	}
	
	public void update(InputHandler input){
		if(input.decrease.isPressed()){
			index--;
			if(index < 0) index += buildings.size();
		}
		if(input.increase.isPressed()){
			index++;
			if(index >= buildings.size()) index -= buildings.size();
		}
	}
	
	public int getXPos(){
		return 30;
	}
	
	public int getYPos(){
		return 30;
	}
	
	public boolean hasSelectedSomething(){
		return !(index == -1);
	}
	
	public void deselect(){
		index = -1;
	}
	
	public void render(Graphics g){
		for(int i = 0; i < buildings.size(); i++){
			Building building = buildings.get(i);
			
			Screen.font.drawLine(g, building.getName(), getXPos(), getYPos() + i * 8);
		}
	}
}
