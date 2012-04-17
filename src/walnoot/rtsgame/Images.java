package walnoot.rtsgame;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images{
	public static BufferedImage[][] terrain = split(load("/terrain2.png"), 16, 16);
	
	public static BufferedImage load(String fileName){
		try {
			return ImageIO.read(Images.class.getResource(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static BufferedImage[][] split(BufferedImage image, int xAmount, int yAmount){
		BufferedImage[][] result = new BufferedImage[xAmount][yAmount];
		
		int width = image.getWidth() / xAmount;
		int height = image.getHeight() / yAmount;
		
		for(int x = 0; x < xAmount; x++){
			for(int y = 0; y < yAmount; y++){
				result[x][y] = image.getSubimage(x * width, y * height, width, height);
			}
		}
		
		return result;
	}
}
