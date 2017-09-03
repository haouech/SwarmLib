package main;

public class Pheromones {
	
	private int width = Variables.WIDTH;
	private int height = Variables.HEIGHT;
//	private double trails[][] = new double[width][height];
	private double EVAPORATION_WEIGHT = 10.0;
	
	public Pheromones() {
	}
	
	public void init_trails() {
		for(int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
				Variables.trails[i][j] = Math.random() * 100;
			}
		}
	}
	
	public void update() {

		for(int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
				Variables.trails[i][j] -= EVAPORATION_WEIGHT;
			}
		}
	}
}
