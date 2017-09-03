package main;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Variables {
	
	public static int ITERATIONS = 5; 
	public static int WIDTH  = 1000;
	public static int HEIGHT  = 1000;
	public static int NUM_ANTS = 10;
	public static int ANT_DISTANCE = 400;
	public static ArrayList<Ant> antList = new ArrayList<Ant>();
	public static BufferedImage image;
	public static int[][] pixels = null;
	public static double[][] heuristic = null;
	public static double[][] trails = null;
	public static double LAMBDA = 0.5;
	public static double alpha = 1.0;
	public static double beta = 2.0;
	public static double THRESHHOLD = 0.0;
}
