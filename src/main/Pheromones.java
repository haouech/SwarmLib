package main;

public class Pheromones {

	private Variables variables = Variables.getInstance();
	
	private int width = variables.WIDTH;
	private int height = variables.HEIGHT;
//	private double trails[][] = new double[width][height];
//	private double EVAPORATION_WEIGHT = 10.0;
	
	public Pheromones() {
	}
	
	public void init_trails() {
		for(int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
				variables.trails[i][j] = variables.TRAIL_INIT;
			}
		}
	}
	
	public void update() {

		for(int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
				variables.trails[i][j] = (1.0 - variables.EVAPORATION_RATE) * variables.trails[i][j];
				variables.trails[i][j] += variables.visited[i][j] * variables.heuristic[i][j];
				variables.trails[i][j] = Math.max(0, variables.trails[i][j]);
			}
		}
	}
	
	public synchronized void deposit_pheromone(Pixel pixel) {
		// update current pixel
		int x = pixel.getX();
		int y = pixel.getY();
		variables.trails[x][y] = (1 - variables.EVAPORATION_RATE) * variables.trails[x][y];
	}
}
