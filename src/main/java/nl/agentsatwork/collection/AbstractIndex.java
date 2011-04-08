package nl.agentsatwork.collection;

import java.util.HashMap;
import java.util.Map;

abstract public class AbstractIndex<A> implements Index<A> {

	private int processed = 0;
	private Map<A, Integer> index = new HashMap<A, Integer>();

	public int indexOf(A value) {
		int i, offset = offset();
		if (processed <= offset) {
			processed = offset;
			index.clear();
		}
		if (index.containsKey(value)) {
			i = index.get(value);
			A result = valueOf(i);
			if (result != null) {
				return i;
			}
		} else {
			i = processed;
		}
		int n = limit();
		for (int j = i; j < n; ++j) {
			A result = valueOf(i);
			if (result != null) {
				index.put(result, j);
				if (value.equals(result)) {
					return j;
				}
			}
		}
		return -1;
	}

}
