package nl.agentsatwork.collection;

import java.util.Collection;
import java.util.Set;

abstract public class AbstractImmutableSet<A> extends AbstractImmutableIterable<A> implements Set<A> {

	public boolean add(A e) {
		throw new UnsupportedOperationException();
	}

	public boolean addAll(Collection<? extends A> c) {
		throw new UnsupportedOperationException();
	}

	public void clear() {
		throw new UnsupportedOperationException();
	}

	public boolean containsAll(Collection<?> c) {
		for (Object o : c) {
			if (!contains(o)) {
				return false;
			}
		}
		return true;
	}

	final public boolean isEmpty() {
		return !iterator().hasNext();
	}

	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	final public Object[] toArray() {
		Object[] result = new Object[size()];
		int i = 0;
		for (Object o : this) {
			result[i++] = o;
		}
		return result;
	}

	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}


}
