package nl.agentsatwork.collection;

import java.util.Set;

abstract public class AbstractSet<A> extends AbstractCollection<A> implements Set<A> {

	public boolean add(A e) {
		if (e == null) {
			throw new NullPointerException();
		}
		Index index = getIndex();
		int i = index.indexOf(e);
		if (i < 0) {
			index.enter(e);
			return true;
		} else {
			return false;
		}
	}

}
