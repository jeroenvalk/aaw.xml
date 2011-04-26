package nl.agentsatwork.collection;

import java.util.Collection;
import java.util.Iterator;

import nl.agentsatwork.aggregates.Index;

abstract public class AbstractImmutableCollection<A> extends AbstractCollection<A> {

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
		final Index index = getIndex();
		return new AbstractImmutableIterator<A>() {

			protected Index getIndex() {
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
