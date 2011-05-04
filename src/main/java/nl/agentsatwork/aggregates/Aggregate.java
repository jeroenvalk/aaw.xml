package nl.agentsatwork.aggregates;

public interface Aggregate {

	AggregateIndex getIndex();

	int enter(Object entity);

	boolean leave(int position);

}
