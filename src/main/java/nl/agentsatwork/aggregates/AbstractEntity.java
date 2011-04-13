package nl.agentsatwork.aggregates;

abstract public class AbstractEntity implements Entity {

	abstract protected void setAggregate(Aggregate aggregate);

	final protected boolean entering(AbstractAggregate aggregate) {
		if (aggregate.entering(this)) {
			assert getAggregate() == null;
			setAggregate(aggregate);
			return true;
		} else {
			return false;
		}
	}

	final protected boolean leaving(AbstractAggregate aggregate) {
		assert getAggregate() == aggregate;
		if (aggregate.leaving(this)) {
			assert getAggregate() == aggregate;
			setAggregate(null);
			return true;
		} else {
			assert getAggregate() == aggregate;
			return false;
		}
	}

	final public boolean enter(Aggregate aggregate) {
		if (aggregate == getAggregate()) {
			return true;
		} else {
			if (aggregate instanceof AbstractAggregate) {
				return entering((AbstractAggregate) aggregate);
			} else {
				return aggregate.enter(this);
			}
		}
	}

	final public boolean leave(Aggregate aggregate) {
		if (aggregate == getAggregate()) {
			if (aggregate instanceof AbstractAggregate) {
				return leaving((AbstractAggregate) aggregate);
			} else {
				return aggregate.leave(this);
			}
		} else {
			return true;
		}
	}

}
