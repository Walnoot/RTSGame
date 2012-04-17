package walnoot.rtsgame.map;

import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class PerlinNoise {
	public static int seed;
	
	public static float noise(int i){
		i = (i << 13) ^ i;
		return (1.0f - ((i * (i * i * 15731 + 789221) + 1376312589) & 0x7fffffff) / 1073741824.0f);
	}
	
	public static float smoothNoise(int x){
		x += seed;
		return noise(x) / 2 + noise(x - 1) / 4 + noise(x + 1) / 4;
	}
	
	public static float interpolate(float a, float b, float x){
		float ft = (float) (x * Math.PI);
		float i = (float) ((1 - Math.cos(ft)) * .5);
		
		return a * (1 - i) + (b * i);
	}
	
	public static float interpolatedNoise(float x){
		int integerX = (int) x;
		float fractional = x - integerX;
		
		return interpolate(smoothNoise(integerX), smoothNoise(integerX + 1), fractional);
	}
	
	public static int pow(int x, int n){
		if(n == 0) return 1;
		else return x * pow(x, n - 1);
	}
	
	public static void main(String[] args){
		BufferedImage result = new BufferedImage(1024, 256, BufferedImage.TYPE_INT_RGB);
		
		seed = new Random().nextInt();
		
		float persistence = 0.25f;
		int numberOctaves = 8;
		
		for(int x = 0; x < result.getWidth(); x++){
			int value = 128;
			
			for(int i = 0; i < numberOctaves; i++){
				float frequency = (float) Math.pow(2, i);
				float amplitude = (float) Math.pow(persistence, i);
				
				value += interpolatedNoise(x / 32f * frequency) * (128 * amplitude);
			}
			
			for(int y = value; y < result.getHeight(); y++){
				try{
					result.setRGB(x, y, 0xFFFFFF);
				}catch(Exception e){
					
				}
			}
			
		}
		
		int option = JOptionPane.showConfirmDialog(null, null, "jisdfrawe", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, new ImageIcon(result));
		
		if(option == JOptionPane.OK_OPTION) main(args);
	}
}
