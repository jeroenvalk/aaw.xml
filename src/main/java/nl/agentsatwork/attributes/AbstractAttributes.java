package nl.agentsatwork.attributes;

import java.util.Map;

import nl.agentsatwork.aggregates.AbstractAggregateIndex;
import nl.agentsatwork.aggregates.Index;
import nl.agentsatwork.attribute.AbstractIndependentAttribute;
import nl.agentsatwork.attribute.Attribute;
import nl.agentsatwork.collection.AbstractMap;

abstract public class AbstractAttributes extends AbstractAggregateIndex
		implements Attributes {

	abstract protected Index getNameIndex();

	public AbstractAttributes(Attribute[] attr) {
		assert super.offset() == 0;
		assert super.limit() == 0;
		Index index = getNameIndex();
		int n = index.limit(), m = attr.length;
		if (m < n)
			n = m;
		for (int i = 0; i < n; ++i) {
			String key = (String) index.valueOf(i);
			if (key == null) {
				if (attr[i] == null) {
					super.autonumerical(null);
				} else {
					throw new IllegalArgumentException();
				}
			} else {
				if (attr[i].getKey() == key) {
					super.enter(attr[i]);
				} else {
					throw new IllegalArgumentException();
				}
			}
		}
		for (int i = n; i<m; ++i) {
			if (attr[i] == null) {
				index.enter(null);
			} else {
				String key = attr[i].getKey();
				if (index.indexOf(key) < 0) {
					index.enter(attr[i].getKey());
				} else {
					throw new IllegalArgumentException();
				}
				super.enter(attr[i]);
			}
		}
	}

	public int autonumerical(Object value) {
		return -1;
	}

	public int offset() {
		int n = getNameIndex().offset();
		for (int i = super.offset(); i < n; ++i) {
			remove(i);
		}
		assert super.offset() == n;
		return n;
	}

	public int limit() {
		Index index = getNameIndex();
		int n = index.limit();
		for (int i = super.limit(); i < n; ++i) {
			String key = (String) index.valueOf(i);
			if (key == null) {
				super.autonumerical(null);
			} else {
				super.enter(new AbstractIndependentAttribute(key) {
					public String defaultValue() {
						return null;
					}
				});
			}
		}
		assert super.limit() == n;
		return n;
	}

	public Object valueOf(int index) {
		limit();
		return super.valueOf(index);
	}

	final public boolean hasAttribute(String name) {
		Index index = getNameIndex();
		int i = index.indexOf(name);
		if (i < 0) {
			return false;
		} else {
			limit();
			Attribute attr = (Attribute) valueOf(i);
			return attr.getValue() != null;
		}
	}

	final public String get(String name) {
		Index index = getNameIndex();
		int i = index.indexOf(name);
		if (i < 0) {
			return null;
		} else {
			Attribute attr = (Attribute) valueOf(i);
			return attr.getValue();
		}
	}

	public void set(String name, String value) {
		Index index = getNameIndex();
		int i = index.indexOf(name);
		if (i < 0) {
			i = index.enter(name);
			if (i < 0) {
				throw new IllegalStateException();
			}
		}
		assert i >= 0;
		limit();
		Attribute attr = (Attribute) valueOf(i);
		attr.setValue(value);
	}

	public Map<String, String> attr() {
		final Index index = this;
		return new AbstractMap<String, String>() {

			public int size() {
				return index.size();
			}

			@Override
			protected Entry<String, String> newEntry(String key, String value) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			protected int indexOfKey(Object key) {
				return getNameIndex().indexOf(key);
			}

			@Override
			protected Index getIndex() {
				return index;
			}

		};
	}

	public int indexOf(Object value) {
		// TODO Auto-generated method stub
		return 0;
	}

}
