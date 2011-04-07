package nl.agentsatwork.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

abstract public class AbstractCollection<A> implements Set<A> {

	abstract Index<A> getIndex();
	
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
			return indexOf((A) o) >= 0;
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
		return new Iterator<A>() {
			private int i = offset(), current = -1;

			public Iterator() {
				
			}
			
			public boolean hasNext() {
				int n = limit();
				if (n > i) {
					while (elementOf(i) == null && ++i < n)
						;
					return i < n;
				} else {
					return false;
				}
			}

			public A next() {
				int n = limit();
				if (n > i) {
					while (elementOf(i) == null && ++i < n)
						;
					if (i < n) {
						assert elementOf(i) != null;
						current = i;
						return elementOf(i++);
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
					elementOf(current, null);
				}
			}

		};
	}

	public boolean remove(Object o) {
		try {
			@SuppressWarnings("unchecked")
			int index = indexOf((A) o);
			if (elementOf(index) == null) {
				return false;
			} else {
				elementOf(index, null);
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
		boolean result = false;
		int n = indexSize();
		for (int i = 0; i < n; ++i) {
			A element = elementOf(i);
			if (element != null && !c.contains(element)) {
				elementOf(i, null);
				result = true;
			}
		}
		return result;
	}

	abstract public int size();

	public Object[] toArray() {
		Object[] result = new Object[size()];
		int j = 0, n = indexSize();
		for (int i = 0; i < n; ++i) {
			A element = elementOf(i);
			if (element != null) {
				result[j++] = element;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		if (size() > a.length) {
			a = (T[]) new Object[size()];
		}
		int j = 0, n = indexSize();
		for (int i = 0; i < n; ++i) {
			T element = (T) elementOf(i);
			if (element != null) {
				a[j++] = element;
			}
		}
		return a;
	}

}
