
public class Ant extends Thread {
	private double x_pos, y_pos;
	
	private boolean finished;
	
	public Ant(double x, double y){
		x_pos = x;
		y_pos = y;
		finished = false;
	}
	
	public void run() {
		while(!finished) {
			// get 8 neighbor pixels
			int pixels[] = read_local_pixels();
		}
	}
	
	private int[] read_local_pixels() {
		// TODO: get pixels from image
		int pixels[] = new int[8];
		return pixels;
	}
}
