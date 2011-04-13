package nl.agentsatwork.aggregates;

import nl.agentsatwork.collection.Index;

abstract public class AbstractAggregate extends AbstractEntity implements
		Aggregate {

	abstract protected Index<Entity> getIndex();

	abstract protected <A> void autonum(A value);

	abstract protected void delete(int i);

	final public boolean entering(AbstractEntity entity) {
		assert entity != null;
		Aggregate aggregate = entity.getAggregate();
		assert aggregate != this;
		Index<Entity> index = getIndex();
		int i = index.indexOf(entity);
		if (i < 0) {
			autonum(entity);
			assert index.valueOf(index.indexOf(entity)) == entity;
			if (entity.leave(aggregate)) {
				return true;
			} else {
				delete(index.indexOf(entity));
				return false;
			}
		} else {
			return index.valueOf(i) == entity;
		}
	}

	final public boolean leaving(AbstractEntity entity) {
		assert entity.getAggregate() == this;
		Index<Entity> index = getIndex();
		int i = index.indexOf(entity);
		if (i < 0) {
			return true;
		} else {
			if (index.valueOf(i) == entity) {
				return leaving(i);
			} else {
				return true;
			}
		}
	}

	private boolean leaving(int index) {
		getIndex().remove(index);
		return true;
	}
	
}
