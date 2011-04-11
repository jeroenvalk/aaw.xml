package nl.agentsatwork.aggregates;

public interface Aggregate {
	
	boolean enter(Entity entity);
	
	boolean leave(Entity entity);

	int indexOf(Entity entity);
	
}
