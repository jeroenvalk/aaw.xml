package nl.agentsatwork.aggregates;

abstract public class AbstractEntity implements Entity {

	protected boolean setAggregate(Aggregate aggregate) {
		return false;
	}

	final public boolean enter(Aggregate aggregate) {
		if (aggregate == getAggregate()) {
			return true;
		} else {
			return aggregate.enter(this);
		}
	}

	final public boolean leave(Aggregate aggregate) {
		if (aggregate == getAggregate()) {
			return aggregate.leave(this);
		} else {
			return true;
		}
	}

}
