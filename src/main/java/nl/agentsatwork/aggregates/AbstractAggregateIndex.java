package nl.agentsatwork.aggregates;

abstract public class AbstractAggregateIndex extends AbstractAggregate
		implements AggregateIndex {

	private int offset = 0, margin = 0, capacity = 0, size = 0;
	private Object[] value = new Object[0], value1 = null;

	public AggregateIndex getIndex() {
		return this;
	}

	public int offset() {
		return offset + margin;
	}

	public int limit() {
		return offset + capacity;
	}

	public Object valueOf(int index) {
		index -= offset;
		if (index < value.length) {
			if (index < margin) {
				return null;
			} else {
				return value[index];
			}
		} else {
			if (index < capacity) {
				index -= value.length;
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
		int n = this.value.length, result = offset + capacity;
		if (value != null)
			++size;
		if (capacity < n) {
			this.value[capacity++] = value;
		} else {
			if (value1 == null) {
				if (n == 0) {
					assert capacity == 0;
					if (value == null) {
						return offset++;
					} else {
						value1 = new Object[1];
					}
				} else {
					value1 = new Object[n];
				}
			}
			capacity -= n;
			assert capacity < value1.length;
			value1[capacity] = value;
			if (++capacity >= value1.length) {
				assert capacity == value1.length;
				capacity += n;
				Object[] aux = new Object[capacity];
				int j = 0;
				for (; j < n; ++j) {
					aux[j] = this.value[j];
				}
				n -= n;
				for (; j < capacity; ++j) {
					aux[j] = value1[n++];
				}
				this.value = aux;
				value1 = null;
			} else {
				capacity += n;
			}
		}
		return result;
	}

	public void remove(int i) {
		int n = value.length;
		i -= offset;
		if (i < capacity) {
			if (i < n) {
				if (margin < i) {
					assert i > 0;
					if (value[i] != null)
						--size;
					value[i] = null;
				} else if (i == margin) {
					assert i >= 0;
					assert value[margin] != null;
					--size;
					value[margin] = null;
					while (++margin < n) {
						if (value[margin] != null)
							return;
					}
					assert margin == n;
					offset += margin;
					capacity -= margin;
					margin -= margin;
					if (value1 == null) {
						assert capacity == 0;
						return;
					}
					value = value1;
					value1 = null;
					--margin;
					while (++margin < capacity) {
						if (value[margin] != null)
							return;
					}
					assert margin == capacity;
					offset += margin;
					capacity -= capacity;
					margin -= margin;
				}
			} else {
				i -= value.length;
				if (value1[i] != null) --size;
				value1[i] = null;
			}
		}
	}
}
