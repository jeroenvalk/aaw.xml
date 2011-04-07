package nl.agentsatwork.attributes;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import nl.agentsatwork.attribute.Attribute;
import nl.agentsatwork.collection.AbstractImmutableSet;

public class ImmutableAttributes extends AbstractEntity implements Attributes {

	public ImmutableAttributes(Attribute[] attribute) {

	}

	final public boolean hasAttribute(String name) {
		return get(name) != null;
	}

	final public String get(String name) {
		return attr().get(name);
	}

	public void set(String name, String value) {
		throw new UnsupportedOperationException();
	}

	public Map<String, String> attr() {
		return new AbstractMap<String, String>() {

			public Set<Entry<String,String>> entrySet() {
				final Set<Entry<String,String>> entrySet = (Set<Entry<String,String>>) this.entrySet();
				return new AbstractImmutableSet<Entry<String,String>>() {

					public boolean contains(Object o) {
						return entrySet.contains(o);
					}

					public Iterator<java.util.Map.Entry<String, String>> iterator() {
						return entrySet.iterator();
					}

					public int size() {
						return entrySet.size();
					}

				};
			}

		};
	}

}
