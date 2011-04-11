package nl.agentsatwork.aggregates;

public interface Entity {

	Aggregate getAggregate();
	
	boolean enter(Aggregate aggregate);
	
	boolean leave(Aggregate aggregate);
	
}
