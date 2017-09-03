package main;

public class Pixel {
	
	private int x,y;
	private int value;
	
	public Pixel() {
		x = 0;
		y = 0;
	}
	
	public Pixel(int x, int y) {
		if(x < 0){
			x = 0;
		} 
		if(y < 0){
			y = 0;
		}
		this.x = x;
		this.y = y;
		value = Variables.pixels[x][y];
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getValue() {
		return value;
	}
}
