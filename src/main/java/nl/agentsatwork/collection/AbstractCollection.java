package nl.agentsatwork.collection;

import java.util.Collection;
import java.util.Iterator;

import nl.agentsatwork.aggregates.Index;

abstract public class AbstractCollection<A> implements Collection<A> {

	abstract protected Index getIndex();

	public boolean add(A e) {
		if (e == null) {
			throw new NullPointerException();
		}
		getIndex().enter(e);
		return true;
	}

	public boolean addAll(Collection<? extends A> c) {
		if (c.contains(null)) {
			throw new NullPointerException();
		}
		boolean result = false;
		for (A a : c) {
			if (add(a))
				result = true;
		}
		return result;
	}

	public void clear() {
		Index index = getIndex();
		int n = index.limit();
		for (int i = index.offset(); i < n; ++i) {
			index.leave(i);
		}
	}

	public boolean contains(Object o) {
		if (o == null) {
			throw new NullPointerException();
		}
		try {
			return getIndex().indexOf(o) >= 0;
		} catch (ClassCastException e) {
			return false;
		}
	}

	public boolean containsAll(Collection<?> c) {
		if (c.contains(null)) {
			throw new NullPointerException();
		}
		for (Object e : c) {
			if (!contains(e)) {
				return false;
			}
		}
		return true;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public Iterator<A> iterator() {
		final Index index = getIndex();
		return new AbstractIterator<A>() {

			protected Index getIndex() {
				return index;
			}

		};
	}

	public boolean remove(Object o) {
		if (o == null) {
			return false;
		}
		try {
			Index index = getIndex();
			int i = index.indexOf(o);
			if (i < 0) {
				return false;
			}
			if (index.valueOf(i) == null) {
				return false;
			} else {
				return index.leave(i);
			}
		} catch (ClassCastException e) {
			return false;
		}
	}

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
		Index index = getIndex();
		boolean result = false;
		int n = index.limit();
		for (int i = index.offset(); i < n; ++i) {
			Object element = index.valueOf(i);
			if (element != null && !c.contains(element)) {
				result = index.leave(i);
			}
		}
		return result;
	}

	public int size() {
		return getIndex().size();
	}

	public Object[] toArray() {
		Index index = getIndex();
		Object[] result = new Object[size()];
		int j = 0, n = index.limit();
		for (int i = index.offset(); i < n; ++i) {
			Object element = index.valueOf(i);
			if (element != null) {
				result[j++] = element;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		Index index = getIndex();
		if (size() > a.length) {
			a = (T[]) new Object[size()];
		}
		int j = 0, n = index.limit();
		for (int i = index.offset(); i < n; ++i) {
			T element = (T) index.valueOf(i);
			if (element != null) {
				a[j++] = element;
			}
		}
		return a;
	}

}
