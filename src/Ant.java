import java.util.Vector;

public class Ant extends Thread {
	private int x_pos, y_pos;
	
	private boolean finished;
	
	public Ant(){
		x_pos = (int)Math.random()*Variables.HEIGHT;
		y_pos = (int)Math.random()*Variables.WIDTH;
		finished = false;
	}
	
	public Ant(int x, int y){
		x_pos = x;
		y_pos = y;
		finished = false;
	}
	
	public void run() {
		while(!finished) {
			// get 8 neighbor pixels
			int pixels[] = read_local_pixels();
			Vector<Pixel> destinations = compute_transitions(pixels);
			destinations.sort(null);
			Pixel next_state = ant_decision(destinations);
			move_to_next_state(next_state);
			deposit_pheromone();
		}
		
		
	}
	
	private void deposit_pheromone() {
		// TODO update current pixel
	}
	
	private void move_to_next_state(Pixel state) {
		// TODO Move the ant to the next state 
	}
	
	private Pixel ant_decision(Vector<Pixel> destinations) {
		// TODO Apply moving decision
		return new Pixel();
	}
	
	private Vector<Pixel> compute_transitions(int[] pixels) {
		// TODO Compute transition probability for each possible pixel 
		return new Vector<Pixel>();
	}
	
	private int[] read_local_pixels() {
		// TODO get pixels from image
		int pixels[] = new int[8];
		return pixels;
	}
}
