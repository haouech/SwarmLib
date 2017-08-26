import java.util.ArrayList;
import java.util.Vector;

public class Ant extends Thread {
	private int x_pos, y_pos;
	private boolean finished;
	private int current_distance;
	private ArrayList<Pixel> path;
	
	public Ant(){
		x_pos = (int)Math.random()*Variables.HEIGHT;
		y_pos = (int)Math.random()*Variables.WIDTH;
		finished = false;
		current_distance = 0;
		path = new ArrayList<Pixel>();
		path.add(new Pixel(x_pos, y_pos));
	}
	
	public Ant(int x, int y){
		x_pos = x;
		y_pos = y;
		finished = false;
		current_distance = 0;
		path.add(new Pixel(x_pos, y_pos));
	}
	
	public void run() {
		while(!finished) {
			// get 8 neighbor pixels
			Vector<Pixel> pixels = read_local_pixels();
			Vector<Pixel> destinations = compute_transitions(pixels);
			destinations.sort(null);
			Pixel next_state = ant_decision(destinations);
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
	
	private Vector<Pixel> compute_transitions(Vector<Pixel> pixels) {
		// TODO Compute transition probability for each possible pixel 
		
		return new Vector<Pixel>();
	}
	
	private Pixel ant_decision(Vector<Pixel> destinations) {
		// TODO Apply moving decision
		return new Pixel();
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
	}
	
	public void die() {
		Variables.antList.remove(this);
	}
}
