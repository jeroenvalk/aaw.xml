package nl.agentsatwork.collection;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

abstract public class AbstractMap<A, B> implements Map<A, B>, Index<Entry<A, B>> {

	abstract protected Entry<A, B> newEntry(A key, B value);

	abstract protected int indexOfKey(A key);

	protected int[] indicesOfValue(B value) {
		int[] aux = new int[size() + 1];
		int j = 1, n = limit();
		for (int i = offset(); i < n; ++i) {
			B value2 = valueOf(i).getValue();
			if (value == value2) {
				aux[0] = i;
				aux[j++] = i;
			} else {
				if (value.equals(value2)) {
					aux[j++] = i;
				}
			}
		}
		int[] result = new int[j];
		for (int i = 0; i < j; ++i) {
			result[i] = aux[i];
		}
		return result;
	}

	public void clear() {
		int n = limit();
		for (int i = 0; i < n; ++i) {
			remove(i);
		}
	}

	@SuppressWarnings("unchecked")
	public boolean containsKey(Object key) {
		if (key == null) {
			throw new NullPointerException();
		} else {
			try {
				return indexOfKey((A) key) >= 0;
			} catch (ClassCastException e) {
				return false;
			}
		}
	}

	@SuppressWarnings("unchecked")
	public boolean containsValue(Object value) {
		if (value == null) {
			throw new NullPointerException();
		} else {
			try {
				return indicesOfValue((B) value).length > 1;
			} catch (ClassCastException e) {
				return false;
			}
		}
	}

	public Set<Entry<A, B>> entrySet() {
		final Index<Entry<A,B>> index = this;
		return new AbstractSet<Entry<A, B>>() {

			protected Index<Entry<A, B>> getIndex() {
				return index;
			}

		};
	}

	public B get(Object key) {
		if (key == null) {
			throw new NullPointerException();
		}
		try {
			@SuppressWarnings("unchecked")
			int index = indexOfKey((A) key);
			if (index < 0) {
				return null;
			}
			return valueOf(index).getValue();
		} catch (ClassCastException e) {
			return null;
		}
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public Set<A> keySet() {
		final Index<Entry<A,B>> self = this;
		final Index<A> index = new Index<A>() {

			public void autonumerical(A value) {
				throw new UnsupportedOperationException();
			}

			public int offset() {
				return self.offset();
			}

			public int limit() {
				return self.limit();
			}

			public int indexOf(A value) {
				return indexOfKey(value);
			}

			public A valueOf(int index) {
				return self.valueOf(index).getKey();
			}

			public void remove(int index) {
				self.remove(index);
			}

			public int size() {
				return self.size();
			}
			
		};
		return new AbstractSet<A>() {

			protected Index<A> getIndex() {
				return index;
			}
			
		};
	}

	public B put(A key, B value) {
		if (key == null || value == null) {
			throw new NullPointerException();
		}
		int index = indexOfKey(key);
		if (index < 0) {
			autonumerical(newEntry(key, value));
			return null;
		} else {
			return valueOf(index).setValue(value);
		}
	}

	public void putAll(Map<? extends A, ? extends B> m) {
		for (Entry<? extends A, ? extends B> entry : m.entrySet()) {
			put(entry.getKey(), entry.getValue());
		}
	}

	public B remove(Object key) {
		if (key == null) {
			throw new NullPointerException();
		}
		try {
			@SuppressWarnings("unchecked")
			int index = indexOfKey((A) key);
			if (index < 0) {
				return null;
			}
			B result = valueOf(index).getValue();
			remove(index);
			return result;
		} catch (ClassCastException e) {
			return null;
		}
	}

	public Collection<B> values() {
		final Index<Entry<A,B>> self = this;
		final Index<B> index = new AbstractIndex<B>() {

			public void autonumerical(B value) {
				throw new UnsupportedOperationException();
			}

			public int offset() {
				return self.offset();
			}

			public int limit() {
				return self.limit();
			}

			public B valueOf(int index) {
				return self.valueOf(index).getValue();
			}

			public void remove(int index) {
				self.remove(index);
			}

			public int size() {
				return self.size();
			}
			
		};
		return new AbstractCollection<B>() {

			protected Index<B> getIndex() {
				return index;
			}
			
		};
	}

}
