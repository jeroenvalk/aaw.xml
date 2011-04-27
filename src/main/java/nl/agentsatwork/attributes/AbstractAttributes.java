package nl.agentsatwork.attributes;

import java.util.Map;

import nl.agentsatwork.aggregates.AbstractAggregateIndex;
import nl.agentsatwork.aggregates.Entity;
import nl.agentsatwork.aggregates.Index;
import nl.agentsatwork.attribute.Attribute;
import nl.agentsatwork.collection.AbstractMap;

abstract public class AbstractAttributes extends AbstractAggregateIndex implements Attributes {

	protected Attribute[] attr;

	abstract protected Index getNameIndex();

	public AbstractAttributes(Attribute[] attribute) {
		attr = attribute;
	}
	
	public int autonumerical(Object value) {
		assert value instanceof Attribute;
		String key = ((Attribute) value).getKey();
		Index index = getNameIndex();
		int i = index.indexOf(key);
		if (i < 0) {
			i = index.enter(key);
			assert i >= 0;
			attr[i] = (Attribute) value;
			return i;
		} else {
			if (attr[i] == null) {
				attr[i] = (Attribute) value;
				return i;
			} else {
				return -1;
			}
		}
	}

	public int offset() {
		return getNameIndex().offset();
	}

	public int limit() {
		return getNameIndex().limit();
	}

	public Entity valueOf(int index) {
		return (Entity) attr[index];
	}

	public void remove(int index) {
		attr[index] = null;
	}

	public int size() {
		throw new UnsupportedOperationException();
	}

	final public boolean hasAttribute(String name) {
		Index index = getNameIndex();
		int i = index.indexOf(name);
		return i >= 0 && attr[i] != null;
	}

	final public String get(String name) {
		Index index = getNameIndex();
		int i = index.indexOf(name);
		if (i < 0) {
			return null;
		} else {
			return attr[i].getValue();
		}
	}

	public void set(String name, String value) {
		Index index = getNameIndex();
		int i = index.indexOf(name);
		
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
