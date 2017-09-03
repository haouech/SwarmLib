package main;

public class Factory {
	
	public Factory() {
	}
	
	public void generate() {
		int antCount = Variables.NUM_ANTS - Variables.antList.size();
		while(antCount-- > 0)
		{
			Ant a = new Ant();
			Variables.antList.add(a);
			a.start();
		}
	}
}
