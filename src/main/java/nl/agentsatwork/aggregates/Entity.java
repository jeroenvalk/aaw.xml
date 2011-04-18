package nl.agentsatwork.aggregates;

public interface Entity {

	Location getLocation();
	
	int enter(Aggregate aggregate);
	
	boolean leave(Aggregate aggregate);
	
}
