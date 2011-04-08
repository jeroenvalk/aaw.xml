package nl.agentsatwork.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

abstract public class AbstractImmutableCollection<A> extends AbstractCollection<A> implements Set<A> {

	public boolean add(A e) {
		throw new UnsupportedOperationException();
	}

	public boolean addAll(Collection<? extends A> c) {
		throw new UnsupportedOperationException();
	}

	public void clear() {
		throw new UnsupportedOperationException();
	}

	public Iterator<A> iterator() {
		final Index<A> index = getIndex();
		return new AbstractImmutableIterator<A>() {

			protected Index<A> getIndex() {
				return index;
			}

		};
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

}
