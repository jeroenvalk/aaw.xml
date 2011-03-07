package nl.agentsatwork.collection;

import java.util.Collection;

abstract public class AbstractSet<A> extends AbstractImmutableSet<A> {

	abstract public boolean add(A e);

	public boolean addAll(Collection<? extends A> c) {
		boolean result = false;
		for (A e : c) {
			if (add(e)) {
				result = true;
			}
		}
		return result;
	}

	public void clear() {
		for (Object o : this) {
			remove(o);
		}
	}

	abstract public boolean remove(Object o);

	public boolean removeAll(Collection<?> c) {
		boolean result = false;
		for (Object o : c) {
			if (remove(o)) {
				result = true;
			}
		}
		return result;
	}

	public boolean retainAll(Collection<?> c) {
		boolean result = false;
		for (Object o : this) {
			if (!c.contains(o)) {
				if (remove(0)) {
					result = true;
				}
			}
		}
		return result;
	}

	@Override
	public void remove(int index, Object current) {
		remove(current);
	}

}
