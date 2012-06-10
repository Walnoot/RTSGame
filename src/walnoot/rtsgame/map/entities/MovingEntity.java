package walnoot.rtsgame.map.entities;

import java.awt.Point;
import java.util.LinkedList;

import walnoot.rtsgame.RTSComponent;
import walnoot.rtsgame.Util;
import walnoot.rtsgame.map.Direction;
import walnoot.rtsgame.map.Map;
import walnoot.rtsgame.map.tribes.Tribe;

public abstract class MovingEntity extends Entity {
	protected double timeTraveled; //hoelang hij onderweg is
	protected Direction direction = Direction.EAST;
	
	private LinkedList<Direction> nextDirections = new LinkedList<Direction>();
	
	public MovingEntity(Map map, int xPos, int yPos, Tribe tribe){
		super(map, xPos, yPos, tribe);
	}
	
	public void update(){
		if(nextDirections.isEmpty()){
			timeTraveled = 0;
			return;
		}
		
		Direction nextDirection = nextDirections.get(0);
		if(nextDirection == null) nextDirection = direction;
		
		timeTraveled += RTSComponent.MS_PER_TICK / (getTravelTime() * (nextDirection.isDiagonal() ? Math.sqrt(2) : 1.0));
		
		while(timeTraveled > 1){
			timeTraveled -= 1;
			
			if(nextDirections.size() == 1){
				timeTraveled = 0;
				onStopMoving();
			}
			
			direction = nextDirection;
			
			xPos += direction.getxOffset();
			yPos += direction.getyOffset();
			
			if(!nextDirections.isEmpty()) nextDirections.remove(0);
		}
	}
	
	protected void onStopMoving(){
	}

	public void moveTo(Point goal){
		LinkedList<Direction> path = Pathfinder.moveTo(new Point(xPos, yPos), goal, map);
		if(path != null) nextDirections = path;
	}
	
	public boolean isMoving(){
		return !nextDirections.isEmpty();
	}
	
	public int getScreenX(){
		int x = super.getScreenX();
		
		Direction direction;
		if(!nextDirections.isEmpty()) direction = nextDirections.get(0);
		else direction = this.direction;
		
		x += Math.round(direction.getPointOnScreen().x * timeTraveled);
		
		return x;
	}
	
	public int getScreenY(){
		int y = super.getScreenY();
		
		Direction direction;
		if(!nextDirections.isEmpty()) direction = nextDirections.get(0);
		else direction = this.direction;
		
		y += Math.round(direction.getPointOnScreen().y * timeTraveled);
		
		return y;
	}
	
	public void moveRandomLocation(int WALK_RANGE){
		int x, y;
		do{
			x = xPos + Util.RANDOM.nextInt(WALK_RANGE * 2) - WALK_RANGE;
			y = yPos + Util.RANDOM.nextInt(WALK_RANGE * 2) - WALK_RANGE;
		}while(map.isSolid(x, y));
	
		moveTo(new Point(x, y));
	}
	
	/** @return tijd die het duurt om over 1 tile te bewegen */
	protected abstract double getTravelTime();

	public abstract int getSelectedOption() ;

	public abstract void setSelectedOption(int indexSelected) ;
}
