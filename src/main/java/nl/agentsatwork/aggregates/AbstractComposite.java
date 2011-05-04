package nl.agentsatwork.aggregates;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class AbstractComposite<A> implements Composite<A> {

	private List<Integer> i = new ArrayList<Integer>(),
			j = new ArrayList<Integer>();
	private List<Index> entries = new ArrayList<Index>();
	private List<Index> indices = new ArrayList<Index>();

	public int i(int k) {
		return i.get(k);
	}

	public int j(int k) {
		return j.get(k);
	}

	@SuppressWarnings("unchecked")
	public int k(int i, int j) {
		return ((Entry<Object, Integer>) entries.get(i).valueOf(j)).getValue();
	}

	public Index getIndex(int i) {
		return indices.get(i);
	}

	@SuppressWarnings("unchecked")
	public A getValue(int k) {
		return (A) indices.get(i(k)).valueOf(j(k));
	}

	public int size() {
		assert i.size() == j.size();
		return i.size();
	}

	public void resize(int size) {
		int n = entries.size();
		if (size < n) {
			for (int i = --n; i <= size; --i) {
				entries.remove(i);
			}
		} else {
			for (int i = n; i < size; ++i) {
				addIndex();
			}
		}
	}

	private void addIndex() {
		final int i1 = entries.size();
		assert indices.size() == i1;
		final Index index1 = newIndex();
		Index index2 = new Index() {

			public int enter(final Object value) {
				final int k = i.size();
				assert j.size() == k;
				Entry<Object, Integer> entry = new Entry<Object, Integer>() {

					private int k = i.size();

					public Object getKey() {
						return value;
					}

					public Integer getValue() {
						return k;
					}

					public Integer setValue(Integer value) {
						int result = k;
						k = value;
						return result;
					}

				};
				i.add(i1);
				final int j1 = index1.enter(entry);
				j.add(j1);
				return j1;
			}

			public int offset() {
				return index1.offset();
			}

			public int limit() {
				return index1.limit();
			}

			public int indexOf(Object value) {
				throw new UnsupportedOperationException();
			}

			@SuppressWarnings("unchecked")
			public Object valueOf(int index) {
				return ((Entry<Integer, Object>) index1.valueOf(index))
						.getValue();
			}

			@SuppressWarnings("unchecked")
			public boolean leave(int index) {
				Entry<Object, Integer> entry = (Entry<Object, Integer>) valueOf(index);
				int pos = entry.getValue();
				int last = i.size();
				assert pos >= 0 && pos < last;
				assert j.size() == last;
				int i1 = i.remove(--last);
				int j1 = j.remove(last);
				entry = (Entry<Object, Integer>) entries.get(i1).valueOf(j1);
				assert entry.getValue() == last;
				entry.setValue(pos);
				i.set(pos, i1);
				j.set(pos, j1);
				return index1.leave(index);
			}

			public int size() {
				return index1.size();
			}

		};
		entries.add(index1);
		indices.add(index2);
	}

	private Index newIndex() {
		return new AbstractIndex() {
			public int indexOf(Object value) {
				throw new UnsupportedOperationException();
			}
		};
	}
}
