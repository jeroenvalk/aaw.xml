package nl.agentsatwork.attributes;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import nl.agentsatwork.attribute.Attribute;
import nl.agentsatwork.attribute.MutableAttribute;
import nl.agentsatwork.collection.AbstractIterator;
import nl.agentsatwork.collection.AbstractSet;

public class MutableAttributes extends ImmutableAttributes {

	public MutableAttributes(Attribute[] attribute) {
		super(attribute);
	}

	public void set(String name, String value) {
		Attribute attribute = attribute(name);
		if (attribute == null) {
			attribute = new MutableAttribute(this, name) {
				protected String defaultValue() {
					return null;
				}				
			};
			attribute.setValue(value);
			if (!this.register(attribute)) {
				throw new IllegalStateException();
			}
		}
		assert name.equals(attribute.getKey());
		attribute.setValue(value);
	}

	public Map<String, String> attr() {
		return new AbstractMap<String, String>() {

			@Override
			public Set<Entry<String, String>> entrySet() {
				return new AbstractSet<Entry<String, String>>() {

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

							@Override
							public Entry<String, String> getNext() {
								String key = it.next();
								return superior.attribute(key);
							}

						};
					}

					public int size() {
						return superior.attributes().size();
					}

					public boolean add(Entry<String, String> e) {
						if (e instanceof Attribute) {
							return superior.register((Attribute) e);
						} else {
							throw new ClassCastException(
									"not an 'nl.agentsatwork.attribute.Attribute'");
						}
					}

					public boolean remove(Object o) {
						if (o instanceof Attribute) {
							return superior.unregister((Attribute) o);
						} else {
							return false;
						}
					}

				};
			}

		};
	}

}
