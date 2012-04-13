package walnoot.rtsgame.map;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class PerlinNoise2D {
	private static int seed = new Random().nextInt();

	public static float noise(int x, int y){
		int n = x + y * 57;
		n += seed;
		return PerlinNoise.noise(n);
	}
	
	public static float smoothNoise(int x, int y){
		float corners = (noise(x - 1, y - 1) + noise(x + 1, y - 1) + noise(x - 1, y + 1) + noise(x + 1, y + 1)) / 16f;
		float sides = (noise(x - 1, y) + noise(x + 1, y) + noise(x, y + 1) + noise(x, y - 1)) / 8f;
		float center = noise(x, y) / 4f;
		
		return center + sides + corners;
	}
	
	public static float interpolatedNoise(float x, float y){
		int intX = (int)x;
		float fracX = x - intX;
		
		int intY = (int)y;
		float fracY = y - intY;
		
		float a = smoothNoise(intX, intY);
		float b = smoothNoise(intX + 1, intY);
		float c = smoothNoise(intX, intY + 1);
		float d = smoothNoise(intX + 1, intY + 1);
		
		float interpolateA = PerlinNoise.interpolate(a, b, fracX);
		float interpolateB = PerlinNoise.interpolate(c, d, fracX);
		
		return PerlinNoise.interpolate(interpolateA, interpolateB, fracY);
	}
	
	/**
	 * @param x x coordinaat
	 * @param y y coordinaat
	 * @param persistence - hoe "grof" de map is
	 * @param zoomlevel - hoever de map ingezoomd is
	 * @param numberOctaves - hoeveel detail er is
	 * @return een float tussen -1 en 1
	 */
	public static float perlinNoise(int x, int y, float persistence, float zoomlevel, int numberOctaves){
		float result = 0;
		
		for(int i = 0; i < numberOctaves; i++){
			float frequency = (float) (1 << i);
			double amplitude = Math.pow(persistence, i);
			
			result += interpolatedNoise(x / zoomlevel * frequency, y / zoomlevel * frequency) * amplitude;
		}
		
		if(result < -1.0f) result = -1.0f;
		if(result > 1.0f) result = 1.0f;
		
		return result;
	}
	
	public static void main(String[] args) throws IOException{
		boolean running = false;
		
		do{
			seed = new Random().nextInt();
			
			running = false;
			
			BufferedImage result = new BufferedImage(1280, 960, BufferedImage.TYPE_INT_RGB);
			//BufferedImage result = new BufferedImage(512, 512, BufferedImage.TYPE_INT_RGB);
			
			float persistence = 0.4f;
			int numberOctaves = 4;
			float zoomlevel = 64f;
			
			for(int x = 0; x < result.getWidth(); x++){
				for(int y = 0; y < result.getHeight(); y++){
					float value = perlinNoise(x, y, persistence, zoomlevel, numberOctaves);
					
					int color = (int) (value * 128);
					color = color << 22;
					
					result.setRGB(x, y, color);
				}
			}
			
			//ImageIO.write(result, "PNG", new File("output.png"));
			
			int option = JOptionPane.showConfirmDialog(null, null, "jisdfrawe", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, new ImageIcon(result));
			
			if(option == JOptionPane.OK_OPTION) running = true;
		}while(running);
	}
}
