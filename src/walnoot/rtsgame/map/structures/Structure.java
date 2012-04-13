package walnoot.rtsgame.map.structures;

import walnoot.rtsgame.map.Map;
import walnoot.rtsgame.map.entities.Entity;
import walnoot.rtsgame.map.tribes.Tribe;

public abstract class Structure extends Entity {
	protected int width, height;
	
	public Structure(Map map, int xPos, int yPos, int width, int height, Tribe tribe) {
		super(map, xPos, yPos, tribe);
		
		this.width = width;
		this.height = height;
	}
	
	public void render() {
		
	}
}
