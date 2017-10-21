package main;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Variables {
	
	private static Variables instance = null;
	
	private Variables() {
		
	}
	
	public static Variables getInstance() {
		if(instance == null) {
			instance = new Variables();
		}
		return instance;
	}
	
	public int ITERATIONS = 40; 
	public int WIDTH  = 1000;
	public int HEIGHT  = 1000;
	public int NUM_ANTS = 10;
	public int ANT_DISTANCE = 300;
	public double TRAIL_INIT = 0.0001;
	public double LAMBDA = 1.0;
	public double alpha = 2.0;
	public double beta = 0.009;
	public double THRESHHOLD = 1500.0;
	public double PHEROMONE_WEIGHT = 5.0;
	public ArrayList<Ant> antList = new ArrayList<Ant>();
	public BufferedImage image;
	public int[][] pixels = null;
	public double[][] heuristic = null;
	public double[][] trails = null;
	public int[][] visited = null;
	public int currentAntNumber = 0;
	public double EVAPORATION_RATE = 0.0009;
}
