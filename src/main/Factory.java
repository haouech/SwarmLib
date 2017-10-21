package main;

import java.util.Iterator;

public class Factory {
	
	private Pheromones pheromones;
	private Variables variables = Variables.getInstance();
	
	public Factory(Pheromones p) {
		pheromones = p;
	}
	
	public void generate() {
		
		int antCount = variables.NUM_ANTS - variables.currentAntNumber;
		while(antCount-- > 0)
		{
			Ant a = new Ant();
			a.setPheromones(pheromones);
			variables.antList.add(a);
			a.start();
			variables.currentAntNumber++;
			try {
				a.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Iterator<Ant> iter = variables.antList.iterator();
		while(iter.hasNext()) {
			Ant a = iter.next();
			try {
				a.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		while(variables.currentAntNumber != 0) {
//			
//		}
	}
}
