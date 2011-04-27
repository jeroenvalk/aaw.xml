package nl.agentsatwork.aggregates;


abstract public class AbstractAggregateIndex extends AbstractAggregate implements AggregateIndex {

	protected AggregateIndex getIndex() {
		return this;
	}

}