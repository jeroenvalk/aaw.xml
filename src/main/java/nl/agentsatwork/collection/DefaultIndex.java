package nl.agentsatwork.collection;

public class DefaultIndex<A> extends AbstractHashIndex<A> implements Index<A> {

	final private int bits;
	private int offset = 0, limit = 0, size = 0;
	private int[] sizes = null;
	private A[][] values = null;

	public DefaultIndex(int bits) {
		this.bits = bits;
		realloc();
	}

	@SuppressWarnings("unchecked")
	private void realloc() {
		if (values == null) {
			values = (A[][]) new Object[1][];
			sizes = new int[1];
		} else {
			int newSize = values.length << 1;
			Object[][] aux = new Object[newSize][];
			int[] aux1 = new int[newSize];
			for (int i = 0; i < values.length; ++i) {
				aux[i] = values[i];
				aux1[i] = sizes[i];
			}
			values = (A[][]) aux;
			sizes = aux1;
		}
	}

	@SuppressWarnings("unchecked")
	public void autonumerical(A value) {
		int n = limit++ - offset;
		if (n == (values.length << bits)) {
			realloc();
		}
		assert n < (values.length << bits);
		int increment = 1 << bits;
		int i = n >> bits;
		int j = n & (--increment);
		if (values[i] == null) {
			values[i] = (A[]) new Object[++increment];
			sizes[i] = 0;
		}
		values[i][j] = value;
		++sizes[i];
		++size;
	}

	public A valueOf(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public void remove(int index) {
		if (index >= offset && index < limit) {
			int i = index >> bits;
			int j = index & ((1 << bits) - 1);
			values[i][j] = null;
			--sizes[i];
			--size;
			if (sizes[0] == 0) {
				int increment = 1 << bits;
				offset += increment;
				for (i = 1; i < sizes.length; ++i) {
					if (sizes[i] == 0) {
						offset += increment;
					} else {
						break;
					}
				}
				for (j = 0; j < i; ++j) {
					values[j] = values[i+j];
					values[i+j] = null;
					sizes[j] = sizes[i+j];
				}
			}
		}
	}

	public int offset() {
		return offset;
	}

	public int limit() {
		return limit;
	}

	public int size() {
		return size;
	}

}
