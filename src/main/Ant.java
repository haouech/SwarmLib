package main;
import java.util.ArrayList;
import java.util.Vector;

public class Ant extends Thread {
	private int x_pos, y_pos;
	private boolean finished;
	private int current_distance;
	private final double TRAIL_WEIGHT = 5.0;
	private ArrayList<Pixel> path;
	private Vector<Double> probabilites;
	
	public Ant(){
		x_pos = (int)Math.random()*Variables.HEIGHT;
		y_pos = (int)Math.random()*Variables.WIDTH;
		finished = false;
		current_distance = 0;
		path = new ArrayList<Pixel>();
		path.add(new Pixel(x_pos, y_pos));
		probabilites = new Vector<Double>();
	}
	
	public Ant(int x, int y){
		x_pos = x;
		y_pos = y;
		finished = false;
		current_distance = 0;
		path = new ArrayList<Pixel>();
		path.add(new Pixel(x_pos, y_pos));
		probabilites = new Vector<Double>();
	}
	
	public void run() {
		while(!finished) {
			// get 8 neighbor pixels
			Vector<Pixel> pixels = read_local_pixels();
			compute_transitions(pixels);
			Pixel next_state = ant_decision(pixels);
			move_to_next_state(next_state);
//			deposit_pheromone();
		}
		for(Pixel pixel : path) {
			deposit_pheromone(pixel);
		}
		die();
	}
	
	private Vector<Pixel> read_local_pixels() {
		// TODO get pixels from image
		Vector<Pixel> pixels = new Vector<Pixel>();
		int dx[] = {0,0,1,-1,1,-1,1,-1};
		int dy[] = {1,-1,0,0,1,-1,-1,1};
		for(int i=0;i<8;i++) {
			pixels.add(new Pixel(x_pos+dx[i], y_pos+dy[i]));
		}
		return pixels;
	}
	
	private void compute_transitions(Vector<Pixel> pixels) {
		// TODO Compute transition probability for each possible pixel 
		double sum = 0.0;
		for(Pixel pixel : pixels) {
			int x = pixel.getX();
			int y = pixel.getY();
			double t = Math.pow(Variables.trails[x][y], (double)Variables.alpha);
			double n = Math.pow(Variables.heuristic[x][y], (double)Variables.beta);
			sum += t*n;
		}
		probabilites.setSize(pixels.size());
		for(int i=0; i<pixels.size(); i++) {
			Pixel pixel = pixels.get(i);
			int x = pixel.getX();
			int y = pixel.getY();
			double t = Math.pow(Variables.trails[x][y], (double)Variables.alpha);
			double n = Math.pow(Variables.heuristic[x][y], (double)Variables.beta);
			probabilites.set(i, t*n/sum);
		}
	}
	
	private Pixel ant_decision(Vector<Pixel> pixels) {
		// TODO Apply moving decision
		double max = 0;
		int idx = 0;
		for(int i=0; i<probabilites.size(); i++) {
			if(probabilites.get(i) > max) {
				max = probabilites.get(i);
				idx = i;
			}
		}
		return pixels.get(idx);
	}
	
	private void move_to_next_state(Pixel state) {
		// TODO Move the ant to the next state 
		current_distance++;
		if(current_distance >= Variables.ANT_DISTANCE) {
			finished = true;
		}
		path.add(state);
		x_pos = state.getX();
		y_pos = state.getY();
	}
	
	private void deposit_pheromone(Pixel pixel) {
		// TODO update current pixel
		int x = pixel.getX();
		int y = pixel.getY();
		Variables.trails[x][y] += TRAIL_WEIGHT;
	}
	
	public void die() {
		Variables.antList.remove(this);
	}
}
