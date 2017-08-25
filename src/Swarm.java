

public class Swarm {
	
	Factory factory = new Factory();
	Pheromones pheromones = new Pheromones();
	
	public void search() {
		for(int i=0; i<Variables.ITERATIONS; i++) {
			factory.generate();
			pheromones.update();
		}
	}
	
}
