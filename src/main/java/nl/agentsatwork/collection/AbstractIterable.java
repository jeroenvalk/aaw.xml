package nl.agentsatwork.collection;

abstract public class AbstractIterable<A> extends AbstractImmutableIterable<A> {

	abstract public void remove(int index, Object current);
	
}
