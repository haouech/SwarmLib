import java.util.ArrayList;

public class Factory {
	
	ArrayList<Ant> antList = new ArrayList<Ant>();
	
	public void generate() {
		while(antList.size() < Variables.NUM_ANTS)
		{
			Ant a = new Ant();
			antList.add(a);
		}
	}
}
