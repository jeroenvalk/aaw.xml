package nl.agentsatwork.collection;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

abstract public class AbstractMap<A, B> implements Map<A, B> {

	private int superAutonum() {
		return autonum();
	}

	private int superIndexSize() {
		return indexSize();
	}

	private int superSize() {
		return size();
	}

	abstract protected Entry<A, B> newEntry(A key, B value);

	abstract protected int autonum();

	abstract protected int indexSize();

	abstract protected int indexOfKey(A key);

	protected int[] indicesOfValue(B value) {
		int[] aux = new int[size() + 1];
		int j = 1, n = indexSize();
		for (int i = 0; i < n; ++i) {
			B value2 = entryOf(i).getValue();
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

	abstract protected Entry<A, B> entryOf(int index);

	abstract protected void entryOf(int index, Entry<A, B> entry);

	public void clear() {
		int n = indexSize();
		for (int i = 0; i < n; ++i) {
			entryOf(i, null);
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

		return new AbstractCollection<Entry<A, B>>() {

			@Override
			protected int autonum() {
				return superAutonum();
			}

			@Override
			protected int indexSize() {
				return superIndexSize();
			}

			@Override
			protected int indexOf(Entry<A, B> element) {

				return indexOfKey(element.getKey());
			}

			@Override
			protected Entry<A, B> elementOf(int index) {
				return entryOf(index);
			}

			@Override
			protected void elementOf(int index, Entry<A, B> element) {
				entryOf(index, element);
			}

			@Override
			public int size() {
				return superSize();
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
			return entryOf(index).getValue();
		} catch (ClassCastException e) {
			return null;
		}
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public Set<A> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	public B put(A key, B value) {
		if (key == null || value == null) {
			throw new NullPointerException();
		}
		int index = indexOfKey(key);
		if (index < 0) {
			index = autonum();
			entryOf(index, newEntry(key, value));
			return null;
		} else {
			B result = entryOf(index).getValue();
			entryOf(index, newEntry(key, value));
			return result;
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
			B result = entryOf(index).getValue();
			entryOf(index, null);
			return result;
		} catch (ClassCastException e) {
			return null;
		}
	}

	abstract public int size();

	public Collection<B> values() {
		// TODO Auto-generated method stub
		return new AbstractCollection<B>() {

			@Override
			protected int autonum() {
				throw new UnsupportedOperationException();
			}

			@Override
			protected int indexSize() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			protected int indexOf(B element) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			protected B elementOf(int index) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			protected void elementOf(int index, B element) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public int size() {
				// TODO Auto-generated method stub
				return 0;
			}
			
		};
	}

}
