package nl.agentsatwork.attributes;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import nl.agentsatwork.attribute.Attribute;
import nl.agentsatwork.collection.AbstractImmutableSet;
import nl.agentsatwork.collection.AbstractIterator;

public class ImmutableAttributes extends AbstractSuperior implements Attributes {

	public ImmutableAttributes(Attribute[] attribute) {
		if (attribute != null) {
			for (int i = 0; i < attribute.length; ++i) {
				register(attribute[i]);
			}
		}
	}

	final public boolean hasAttribute(String name) {
		return get(name) != null;
	}

	final public String get(String name) {
		if (name == null) {
			throw new IllegalArgumentException();
		} else {
			Attribute result = attribute(name);
			if (result == null) {
				return null;
			} else {
				assert name.equals(result.getKey());
				return result.getValue();
			}
		}
	}

	public void set(String name, String value) {
		throw new UnsupportedOperationException();
	}

	public Map<String, String> attr() {
		final Superior superior = this;
		return new AbstractMap<String, String>() {

			public Set<Entry<String, String>> entrySet() {
				return new AbstractImmutableSet<Entry<String, String>>() {

					public boolean contains(Object o) {
						if (o instanceof Attribute) {
							String key = ((Attribute) o).getKey();
							return superior.attribute(key) == o;
						} else {
							return false;
						}
					}

					public Iterator<Entry<String, String>> iterator() {
						final Iterator<String> it = superior.attributes()
								.iterator();
						return new AbstractIterator<Entry<String, String>>(this) {

							public boolean hasNext() {
								return it.hasNext();
							}

							public Entry<String, String> getNext() {
								String key = it.next();
								return superior.attribute(key);
							}

						};
					}

					public int size() {
						return superior.attributes().size();
					}

				};
			}

		};
	}

	public String valueOf(String name) {
		return null;
	}

}
