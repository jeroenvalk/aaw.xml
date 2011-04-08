package nl.agentsatwork.collection;

import java.util.Iterator;

abstract public class AbstractImmutableIterator<A> implements Iterator<A> {

	protected int i = getIndex().offset();

	abstract protected Index<A> getIndex();
	
	public boolean hasNext() {
		Index<A> index = getIndex();
		int n = index.limit();
		if (n > i) {
			while (index.valueOf(i) == null && ++i < n)
				;
			return i < n;
		} else {
			return false;
		}
	}

	public A next() {
		Index<A> index = getIndex();
		int n = index.limit();
		if (n > i) {
			while (index.valueOf(i) == null && ++i < n)
				;
			if (i < n) {
				assert index.valueOf(i) != null;
				return index.valueOf(i++);
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
