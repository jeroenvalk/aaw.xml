package nl.agentsatwork.aggregates;


abstract public class AbstractAggregate extends AbstractEntity implements
		Aggregate {

	final protected int entering(AbstractEntity entity) {
		assert entity != null;
		Aggregate aggregate = entity.getLocation().getAggregate();
		assert aggregate != this;
		AggregateIndex index = getIndex();
		int i = index.autonumerical(entity);
		if (i >= 0) {
			assert index.valueOf(i) == entity;
			if (aggregate != null && !entity.leave(aggregate)) {
				index.remove(i);
				return -1;
			}
		}
		return i;
	}

	final protected boolean leaving(AbstractEntity entity, int position) {
		assert entity.getLocation().getAggregate() == this;
		AggregateIndex index = getIndex();
		if (position < 0) {
			position = index.indexOf(entity);
			if (position < 0) {
				return true;
			}
		}
		if (index.valueOf(position) == entity) {
			index.remove(position);
		}
		return true;
	}

	public int enter(Object value) {
		if (value instanceof Entity) {
			return ((Entity) value).enter(this);
		} else {
			AggregateIndex index = getIndex();
			int i = index.indexOf(value);
			if (i < 0 || index.valueOf(i) != value) {
				return index.autonumerical(value);
			} else {
				return i;
			}
		}
	}

	public boolean leave(int position) {
		AggregateIndex index = getIndex();
		Object value = index.valueOf(position);
		if (value instanceof Entity) {
			return ((Entity) value).leave(this);
		} else {
			index.remove(position);
			return true;
		}
	}
}
