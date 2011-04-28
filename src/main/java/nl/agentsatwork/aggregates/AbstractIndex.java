package nl.agentsatwork.aggregates;

abstract public class AbstractIndex implements AggregateIndex {

	private int offset = 0, offset1 = 0, limit = 0, size = 0;
	private Object[] value = new Object[0], value1 = null;

	public int enter(Object entity) {
		return autonumerical(entity);
	}

	public boolean leave(int position) {
		remove(position);
		return true;
	}

	public int offset() {
		return offset;
	}

	public int limit() {
		return limit;
	}

	public Object valueOf(int index) {
		if (index < offset1) {
			if (index < 0) {
				return null;
			} else {
				return value[index];
			}
		} else {
			if (index < limit) {
				return value1[index];
			} else {
				return null;
			}
		}
	}

	public int size() {
		return size;
	}

	public int autonumerical(Object value) {
		if (value1 == null) {
			int n = this.value.length;
			if (n == 0)
				++n;
			value1 = new Object[n];
		}
		int i = limit - offset1;
		assert i < value1.length;
		value1[i] = value;
		if (++i == value1.length) {
			Object[] aux = new Object[this.value.length + value1.length];
			for (i = 0; i < this.value.length; ++i) {
				aux[i] = this.value[i];
			}
			for (i = 0; i < value1.length; ++i) {
				aux[i + this.value.length] = value1[i];
			}
			this.value = aux;
			value1 = null;
		}
		return limit++;
	}

	public void remove(int i) {
		if (i < offset1) {
			if (i >= 0) {
				value[i] = null;
			}
		} else {
			if (i < limit) {
				value1[i] = null;
			}
		}
	}

}
