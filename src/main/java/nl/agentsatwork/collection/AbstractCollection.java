package nl.agentsatwork.collection;

import java.util.Collection;
import java.util.Iterator;

abstract public class AbstractCollection<A> implements Collection<A> {

	abstract protected Index<A> getIndex();

	public boolean add(A e) {
		if (e == null) {
			throw new NullPointerException();
		}
		getIndex().autonumerical(e);
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
		Index<A> index = getIndex();
		int n = index.limit();
		for (int i = index.offset(); i < n; ++i) {
			index.remove(i);
		}
	}

	@SuppressWarnings("unchecked")
	public boolean contains(Object o) {
		if (o == null) {
			throw new NullPointerException();
		}
		try {
			return getIndex().indexOf((A) o) >= 0;
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
		final Index<A> index = getIndex();
		return new AbstractIterator<A>() {

			protected Index<A> getIndex() {
				return index;
			}

		};
	}

	public boolean remove(Object o) {
		if (o == null) {
			return false;
		}
		try {
			Index<A> index = getIndex();
			@SuppressWarnings("unchecked")
			int i = index.indexOf((A) o);
			if (i < 0) {
				return false;
			}
			if (index.valueOf(i) == null) {
				return false;
			} else {
				index.remove(i);
				return true;
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
		Index<A> index = getIndex();
		boolean result = false;
		int n = index.limit();
		for (int i = index.offset(); i < n; ++i) {
			A element = index.valueOf(i);
			if (element != null && !c.contains(element)) {
				index.remove(i);
				result = true;
			}
		}
		return result;
	}

	public int size() {
		return getIndex().size();
	}

	public Object[] toArray() {
		Index<A> index = getIndex();
		Object[] result = new Object[size()];
		int j = 0, n = index.limit();
		for (int i = index.offset(); i < n; ++i) {
			A element = index.valueOf(i);
			if (element != null) {
				result[j++] = element;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		Index<A> index = getIndex();
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
