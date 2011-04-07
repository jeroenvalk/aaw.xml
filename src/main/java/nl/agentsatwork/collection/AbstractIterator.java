package nl.agentsatwork.collection;

import java.util.Iterator;

abstract public class AbstractIterator<A> implements Iterator<A> {

	private int i = getIndex().offset(), current = -1;

	abstract protected Index<A> getIndex();
	
	public boolean hasNext() {
		int n = getIndex().limit();
		if (n > i) {
			while (getIndex().valueOf(i) == null && ++i < n)
				;
			return i < n;
		} else {
			return false;
		}
	}

	public A next() {
		int n = getIndex().limit();
		if (n > i) {
			while (getIndex().valueOf(i) == null && ++i < n)
				;
			if (i < n) {
				assert getIndex().valueOf(i) != null;
				current = i;
				return getIndex().valueOf(i++);
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
			getIndex().valueOf(current, null);
		}
	}
	
	
}
