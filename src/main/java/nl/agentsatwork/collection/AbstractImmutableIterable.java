package nl.agentsatwork.collection;

abstract public class AbstractImmutableIterable<A> implements Iterable<A> {

	public void remove(int index, Object current) {
		throw new UnsupportedOperationException();
	}

}
