package nl.agentsatwork.aggregates;

public class RootStructure implements Structure {

	final private Index index;
	
	public RootStructure(final Object[] value) {
		index = new Index() {

			public int enter(Object value) {
				throw new UnsupportedOperationException();
			}

			public int offset() {
				return 0;
			}

			public int limit() {
				return value.length;
			}

			public int indexOf(Object value) {
				throw new UnsupportedOperationException();
			}

			public Object valueOf(int index) {
				return value[index];
			}

			public boolean leave(int index) {
				throw new UnsupportedOperationException();
			}

			public int size() {
				return value.length;
			}
			
		};
	}

	public int i(int k) {
		return 0;
	}

	public int j(int k) {
		return k;
	}

	public int k(int i, int j) {
		switch(i) {
		case 0: return j;
		default: return -1;
		}
	}

	public Index getIndex(int i) {
		return index;
	}

	public Object getValue(int k) {
		return index.valueOf(k);
	}

	public void resize(int size) {
		throw new UnsupportedOperationException();
	}

	public int size() {
		return index.size();
	}

	public void refresh() {
		
	}

	public void refresh(int i) {
		
	}

	public AggregateIndex getAggregateIndex() {
		return null;
	}
}
