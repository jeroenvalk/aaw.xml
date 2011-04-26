package nl.agentsatwork.aggregates;


abstract public class AbstractIndex extends AbstractAggregate implements AggregateIndex {

	protected AggregateIndex getIndex() {
		return this;
	}

}