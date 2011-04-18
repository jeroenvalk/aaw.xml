package nl.agentsatwork.aggregates;

abstract public class AbstractEntity implements Entity {

	final private int entering(AbstractAggregate aggregate) {
		assert aggregate != null;
		int i = aggregate.entering(this);
		if (i >= 0) {
			Location location = getLocation();
			assert location.getAggregate() == null;
			location.setAggregate(aggregate);
			location.setPosition(i);
		}
		return i;
	}

	final private boolean leaving(AbstractAggregate aggregate) {
		assert aggregate != null;
		Location location = getLocation();
		assert location.getAggregate() == aggregate;
		if (aggregate.leaving(this, location.getPosition())) {
			assert location.getAggregate() == aggregate;
			location.setAggregate(null);
			return true;
		} else {
			assert location.getAggregate() == aggregate;
			return false;
		}
	}

	final public int enter(Aggregate aggregate) {
		if (aggregate == null) {
			throw new IllegalArgumentException();
		}
		Location location = getLocation();
		if (aggregate == location.getAggregate()) {
			return location.getPosition();
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
		Location location = getLocation();
		if (aggregate == location.getAggregate()) {
			if (aggregate instanceof AbstractAggregate) {
				return leaving((AbstractAggregate) aggregate);
			} else {
				return aggregate.leave(location.getPosition());
			}
		} else {
			return true;
		}
	}

}
