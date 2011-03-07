package nl.agentsatwork.collection;

import java.util.Iterator;

abstract public class AbstractIterator<A> implements Iterator<A> {
	
	final private AbstractImmutableIterable<A> parent;
	
	private A current = null;
	private int index = -1;
	
	public AbstractIterator(AbstractImmutableIterable<A> parent) {
		this.parent = parent;
	}

	final public void remove() {
		if (current == null) {
			throw new IllegalStateException();
		}
		parent.remove(index, current);
		current = null;
	}

	final public A next() {
		current = getNext();
		return current;
	}

	abstract public A getNext();
	
}
