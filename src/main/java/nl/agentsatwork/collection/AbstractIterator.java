package nl.agentsatwork.collection;

import java.util.Iterator;

import nl.agentsatwork.aggregates.Index;

abstract public class AbstractIterator<A> extends AbstractImmutableIterator<A> implements Iterator<A> {

	private int current = -1;

	abstract protected Index getIndex();
	
	@SuppressWarnings("unchecked")
	public A next() {
		Index index = getIndex();
		int n = index.limit();
		if (n > i) {
			while (index.valueOf(i) == null && ++i < n)
				;
			if (i < n) {
				assert index.valueOf(i) != null;
				current = i;
				return (A) index.valueOf(i++);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public void remove() {
		if (current < 0) {
			throw new IllegalStateException();
		} else {
			getIndex().leave(current);
		}
	}
	
	
}
