package nl.agentsatwork.collection;

import nl.agentsatwork.aggregates.AbstractAggregate;

abstract public class AbstractIndex extends AbstractAggregate implements AggregateIndex {

	protected AggregateIndex getIndex() {
		return this;
	}

}