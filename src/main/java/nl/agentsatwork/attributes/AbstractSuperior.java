package nl.agentsatwork.attributes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import nl.agentsatwork.attribute.Attribute;
import nl.agentsatwork.collection.AbstractImmutableSet;
import nl.agentsatwork.collection.AbstractIterator;

abstract public class AbstractSuperior implements Superior {

	final private Map<String, Attribute> attr = new HashMap<String, Attribute>();

	final public Set<String> attributes() {
		return new AbstractImmutableSet<String>() {

			public boolean contains(Object o) {
				return attr.containsKey(o);
			}

			public AbstractIterator<String> iterator() {
				final Iterator<String> it = attr.keySet().iterator();
				return new AbstractIterator<String>(this) {

					public boolean hasNext() {
						return it.hasNext();
					}

					@Override
					public String getNext() {
						return it.next();
					}

				};
			}

			public int size() {
				return attr.keySet().size();
			}

		};
	}

	final public Attribute attribute(String key) {
		return attr.get(key);
	}

	final public boolean register(Attribute attribute) {
		String key = attribute.getKey();
		Attribute org = attr.put(key, attribute);
		if (org == null) {
			return true;
		} else {
			assert key.equals(org.getKey());
			attr.put(key, org);
			return false;
		}
	}

	final public boolean unregister(Attribute attribute) {
		String key = attribute.getKey();
		Attribute org = attr.remove(key);
		if (org == attribute) {
			return true;
		} else {
			if (org == null) {
				return false;
			} else {
				assert key.equals(org.getKey());
				attr.put(key, org);
				return false;
			}
		}
	}

	abstract public String valueOf(String name);

}
