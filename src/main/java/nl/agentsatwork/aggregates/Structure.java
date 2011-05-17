package nl.agentsatwork.aggregates;

public interface Structure extends Composite<Object> {

	AggregateIndex getAggregateIndex();

	void refresh();

	void refresh(int i);

}
