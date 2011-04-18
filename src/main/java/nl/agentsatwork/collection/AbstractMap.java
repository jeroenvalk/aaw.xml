package nl.agentsatwork.collection;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import nl.agentsatwork.aggregates.Location;

abstract public class AbstractMap<A, B> implements Map<A, B> {

	abstract protected Entry<A, B> newEntry(A key, B value);

	abstract protected int indexOfKey(Object key);

	abstract protected Index getIndex();
	
	protected int[] indicesOfValue(B value) {
		Index index = getIndex();
		int[] aux = new int[size() + 1];
		int j = 1, n = index.limit();
		for (int i = index.offset(); i < n; ++i) {
			@SuppressWarnings("unchecked")
			Object value2 = ((Entry<String,String>) index.valueOf(i)).getValue();
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
		Index index = getIndex();
		int n = index.limit();
		for (int i = 0; i < n; ++i) {
			index.leave(i);
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
		final Index index = getIndex();
		return new AbstractSet<Entry<A, B>>() {

			protected Index getIndex() {
				return index;
			}

		};
	}

	@SuppressWarnings("unchecked")
	public B get(Object key) {
		if (key == null) {
			throw new NullPointerException();
		}
		try {
			int index = indexOfKey(key);
			if (index < 0) {
				return null;
			}
			return ((Entry<A, B>) getIndex().valueOf(index)).getValue();
		} catch (ClassCastException e) {
			return null;
		}
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public Set<A> keySet() {
		final Index self = getIndex();
		final Index index = new Index() {

			public int enter(Object value) {
				throw new UnsupportedOperationException();
			}

			public int offset() {
				return self.offset();
			}

			public int limit() {
				return self.limit();
			}

			public int indexOf(Object value) {
				return indexOfKey(value);
			}

			@SuppressWarnings("unchecked")
			public A valueOf(int index) {
				return ((Entry<A, B>) self.valueOf(index)).getKey();
			}

			public boolean leave(int index) {
				return self.leave(index);
			}

			public int size() {
				return self.size();
			}
			
		};
		return new AbstractSet<A>() {

			protected Index getIndex() {
				return index;
			}
			
		};
	}

	@SuppressWarnings("unchecked")
	public B put(A key, B value) {
		if (key == null || value == null) {
			throw new NullPointerException();
		}
		Index index = getIndex();
		int i = indexOfKey(key);
		if (i < 0) {
			index.enter(newEntry(key, value));
			return null;
		} else {
			return ((Entry<A, B>) index.valueOf(i)).setValue(value);
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
			int index = indexOfKey(key);
			if (index < 0) {
				return null;
			}
			@SuppressWarnings("unchecked")
			B result = ((Entry<A, B>) getIndex().valueOf(index)).getValue();
			getIndex().leave(index);
			return result;
		} catch (ClassCastException e) {
			return null;
		}
	}

	public Collection<B> values() {
		final Index self = getIndex();
		final Index index = new AbstractHashIndex() {

			public int autonumerical(Object value) {
				throw new UnsupportedOperationException();
			}

			public int offset() {
				return self.offset();
			}

			public int limit() {
				return self.limit();
			}

			@SuppressWarnings("unchecked")
			public B valueOf(int index) {
				return ((Entry<A, B>) self.valueOf(index)).getValue();
			}

			public void remove(int index) {
				self.leave(index);
			}

			public int size() {
				return self.size();
			}

			public Location getLocation() {
				return null;
			}

		};
		return new AbstractCollection<B>() {

			protected Index getIndex() {
				return index;
			}
			
		};
	}

}
