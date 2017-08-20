
public class Pheromones {
	
	private int width = Variables.WIDTH;
	private int height = Variables.HEIGHT;
	private double trails[][] = new double[width][height];
	
	public void init_trails() {
		for(int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
				trails[i][j] = Math.random();
			}
		}
	}
	
	public void update() {
		
	}
}
