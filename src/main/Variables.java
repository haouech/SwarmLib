package main;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Variables {
	
	public static int ITERATIONS = 40; 
	public static int WIDTH  = 1000;
	public static int HEIGHT  = 1000;
	public static int NUM_ANTS = 10;
	public static int ANT_DISTANCE = 300;
	public static double TRAIL_INIT = 0.0001;
	public static double LAMBDA = 1.0;
	public static double alpha = 2.0;
	public static double beta = 0.009;
	public static double THRESHHOLD = 50.0;
	public static double PHEROMONE_WEIGHT = 5.0;
	public static ArrayList<Ant> antList = new ArrayList<Ant>();
	public static BufferedImage image;
	public static int[][] pixels = null;
	public static double[][] heuristic = null;
	public static double[][] trails = null;
	public static int[][] visited = null;
	public static int currentAntNumber = 0;
	public static double EVAPORATION_RATE = 0.0009;
}
