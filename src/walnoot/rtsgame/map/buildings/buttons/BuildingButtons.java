package walnoot.rtsgame.map.buildings.buttons;

import java.awt.*;
import java.awt.image.*;

import javax.imageio.ImageIO;

import walnoot.rtsgame.map.*;

public abstract class BuildingButtons {
	BufferedImage Button;
	
	
	public abstract void render(Graphics g);
	public abstract void update(Point mousePos, boolean mouseIsDown, Point translation, boolean isOnBar);
	public abstract void isSelected();
	public abstract void isDeselected();
	
	
}
