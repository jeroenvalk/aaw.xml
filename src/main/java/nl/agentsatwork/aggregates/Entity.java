package nl.agentsatwork.aggregates;

public interface Entity {

	Aggregate getAggregate();

	int getPosition();
	
	int enter(Aggregate aggregate);
	
	boolean leave(Aggregate aggregate);
	
}
