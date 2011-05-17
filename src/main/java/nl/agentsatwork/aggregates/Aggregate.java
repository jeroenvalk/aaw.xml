package nl.agentsatwork.aggregates;

public interface Aggregate extends Entity {

	AggregateIndex getIndex();

	int enter(Object entity);

	boolean leave(int position);

}
