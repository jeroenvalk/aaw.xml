package nl.agentsatwork.collection;

import java.util.Iterator;

import nl.agentsatwork.aggregates.Index;

abstract public class AbstractImmutableIterator<A> implements Iterator<A> {

	protected int i = getIndex().offset();

	abstract protected Index getIndex();
	
	public boolean hasNext() {
		Index index = getIndex();
		int n = index.limit();
		if (n > i) {
			while (index.valueOf(i) == null && ++i < n)
				;
			return i < n;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public A next() {
		Index index = getIndex();
		int n = index.limit();
		if (n > i) {
			while (index.valueOf(i) == null && ++i < n)
				;
			if (i < n) {
				assert index.valueOf(i) != null;
				return (A) index.valueOf(i++);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

}
