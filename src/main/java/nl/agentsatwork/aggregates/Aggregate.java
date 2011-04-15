package nl.agentsatwork.aggregates;

public interface Aggregate {

	int enter(Object entity);

	boolean leave(Object entity, int position);

}
