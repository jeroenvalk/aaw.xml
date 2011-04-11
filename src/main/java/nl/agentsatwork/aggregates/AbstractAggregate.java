package nl.agentsatwork.aggregates;

import nl.agentsatwork.collection.Index;

abstract public class AbstractAggregate implements Aggregate {

	abstract protected Index<Entity> getIndex();

	final public boolean enter(Entity entity) {
		Aggregate aggregate = entity.getAggregate();
		if (aggregate == this) {
			assert getIndex().indexOf(entity) >= 0;
			assert getIndex().valueOf(getIndex().indexOf(entity)) == entity;
			return true;
		} else {
			if (entity instanceof AbstractEntity) {
				Index<Entity> index = getIndex();
				int i = index.indexOf(entity);
				if (i < 0) {
					index.autonumerical(entity);
					if (aggregate.leave(entity)) {
						if (((AbstractEntity) entity).setAggregate(this)) {
							return true;
						} else {
							throw new AssertionError();
						}
					} else {
						i = index.indexOf(entity);
						assert index.valueOf(i) == entity;
						index.remove(i);
						return false;
					}
				} else {
					assert index.valueOf(i) != entity;
					return false;
				}
			} else {
				return entity.enter(this);
			}
		}
	}

	final public boolean leave(Entity entity) {
		if (entity.getAggregate() == this) {
			if (entity instanceof AbstractEntity) {
				Index<Entity> index = getIndex();
				int i = index.indexOf(entity);
				assert index.valueOf(i) == entity;
				if (((AbstractEntity) entity).setAggregate(null)) {
					index.remove(i);
					return true;
				} else {
					return false;
				}
			} else {
				return entity.leave(this);
			}
		} else {
			return true;
		}
	}
}
