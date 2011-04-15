package nl.agentsatwork.aggregates;

public interface Aggregate {

	int enter(Object entity);

	boolean leave(int position);

}
