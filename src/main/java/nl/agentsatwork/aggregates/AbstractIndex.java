package nl.agentsatwork.aggregates;

abstract public class AbstractIndex extends AbstractAggregateIndex {

	public int enter(Object entity) {
		return autonumerical(entity);
	}

	public boolean leave(int position) {
		remove(position);
		return true;
	}

}
