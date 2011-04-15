package nl.agentsatwork.aggregates;

abstract public class AbstractEntity implements Entity {

	final private int entering(AbstractAggregate aggregate) {
		assert aggregate != null;
		int i = aggregate.entering(this);
		if (i >= 0) {
			assert getAggregate() == null;
			setAggregate(aggregate);
			setPosition(i);
		}
		return i;
	}

	final private boolean leaving(AbstractAggregate aggregate) {
		assert aggregate != null;
		assert getAggregate() == aggregate;
		if (aggregate.leaving(this, getPosition())) {
			assert getAggregate() == aggregate;
			setAggregate(null);
			return true;
		} else {
			assert getAggregate() == aggregate;
			return false;
		}
	}

	abstract protected void setAggregate(Aggregate aggregate);

	abstract protected void setPosition(int i);
	
	final public int enter(Aggregate aggregate) {
		if (aggregate == null) {
			throw new IllegalArgumentException();
		}
		if (aggregate == getAggregate()) {
			return getPosition();
		} else {
			if (aggregate instanceof AbstractAggregate) {
				return entering((AbstractAggregate) aggregate);
			} else {
				return aggregate.enter(this);
			}
		}
	}

	final public boolean leave(Aggregate aggregate) {
		if (aggregate == null) {
			throw new IllegalArgumentException();
		}
		if (aggregate == getAggregate()) {
			if (aggregate instanceof AbstractAggregate) {
				return leaving((AbstractAggregate) aggregate);
			} else {
				return aggregate.leave(this, getPosition());
			}
		} else {
			return true;
		}
	}

}
