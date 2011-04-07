package nl.agentsatwork.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

abstract public class AbstractCollection<A> implements Set<A> {

	abstract protected Index<A> getIndex();
	
	public boolean add(A e) {
		if (e == null) {
			throw new NullPointerException();
		}
		int index = getIndex().indexOf(e);
		if (index < 0) {
			getIndex().valueOf(getIndex().autonumerical(), e);
			return true;
		} else {
			return false;
		}
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
		int n = getIndex().limit();
		for (int i = getIndex().offset(); i < n; ++i) {
			getIndex().valueOf(i, null);
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
		Index<A> index = getIndex();
		try {
			@SuppressWarnings("unchecked")
			int i = index.indexOf((A) o);
			if (index.valueOf(i) == null) {
				return false;
			} else {
				index.valueOf(i, null);
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
				index.valueOf(i, null);
				result = true;
			}
		}
		return result;
	}

	abstract public int size();

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
