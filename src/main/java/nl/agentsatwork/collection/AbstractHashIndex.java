package nl.agentsatwork.collection;

import java.util.HashMap;
import java.util.Map;

abstract public class AbstractHashIndex extends AbstractIndex implements Index {

	private int processed = 0;
	private Map<Object, Integer> index = new HashMap<Object, Integer>();

	public int indexOf(Object value) {
		int i, offset = offset();
		if (processed <= offset) {
			processed = offset;
			index.clear();
		}
		if (index.containsKey(value)) {
			i = index.get(value);
			Object result = valueOf(i);
			if (result != null) {
				return i;
			}
		} else {
			i = processed;
		}
		int n = limit();
		for (int j = i; j < n; ++j) {
			Object result = valueOf(i);
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
