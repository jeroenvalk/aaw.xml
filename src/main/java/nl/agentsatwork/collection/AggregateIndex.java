package nl.agentsatwork.collection;

import nl.agentsatwork.aggregates.Aggregate;

public interface AggregateIndex extends Aggregate, Index {

	int autonumerical(Object value);

	void remove(int i);

}
