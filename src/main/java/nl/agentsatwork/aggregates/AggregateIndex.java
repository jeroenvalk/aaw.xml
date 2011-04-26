package nl.agentsatwork.aggregates;


public interface AggregateIndex extends Aggregate, Index {

	int autonumerical(Object value);

	void remove(int i);

}
