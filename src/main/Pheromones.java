package main;

public class Pheromones {
	
	private int width = Variables.WIDTH;
	private int height = Variables.HEIGHT;
//	private double trails[][] = new double[width][height];
//	private double EVAPORATION_WEIGHT = 10.0;
	
	public Pheromones() {
	}
	
	public void init_trails() {
		for(int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
				Variables.trails[i][j] = Variables.TRAIL_INIT;
			}
		}
	}
	
	public void update() {

		for(int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
				Variables.trails[i][j] = (1.0 - Variables.EVAPORATION_RATE) * Variables.trails[i][j];
				Variables.trails[i][j] += Variables.visited[i][j] * Variables.heuristic[i][j];
				Variables.trails[i][j] = Math.max(0, Variables.trails[i][j]);
			}
		}
	}
	
	public static synchronized void deposit_pheromone(Pixel pixel) {
		// update current pixel
		int x = pixel.getX();
		int y = pixel.getY();
		Variables.trails[x][y] = (1 - Variables.EVAPORATION_RATE) * Variables.trails[x][y];
	}
}
