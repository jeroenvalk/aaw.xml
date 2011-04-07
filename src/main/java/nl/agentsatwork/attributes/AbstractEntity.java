package nl.agentsatwork.attributes;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import nl.agentsatwork.attribute.AbstractAttribute;
import nl.agentsatwork.attribute.Attribute;
import nl.agentsatwork.collection.AbstractIterator;
import nl.agentsatwork.collection.AbstractSet;
import nl.agentsatwork.elements.Superior;
import nl.agentsatwork.xpath.AbstractXPath;

public class AbstractEntity extends AbstractXPath implements Entity {

	private Superior aggregate = null;
	private AbstractAttribute[] attr = null;

	final public int index(AbstractAttribute attribute) {
		for (int i = 0; i < attr.length; ++i) {
			if (attr[i] == attribute) {
				return i;
			}
		}
		return -1;
	}

	final public int index(String name) {
		return aggregate.index(name);
	}

	final public String name(int index) {
		return aggregate.name(index);
	}

	final public AbstractAttribute attribute(int index) {
		return attr[index];
	}

	final public boolean register(AbstractAttribute attribute) {
		Entity entity = attribute.getSuperior();
		if (entity == null) {
			if (index(attribute) < 0) {
				entity = AbstractAttribute.defaultEntity;
				int index = entity.index(attribute);
				if (index < 0) {
					return false;
				} else {
					String name = entity.name(index);
					register(name, attribute);
					attribute.setEntity(this);
					return true;
				}
			} else {
				return true;
			}
		} else if (entity == this) {
			if (index(attribute) < 0) {
				throw new IllegalStateException();
			} else {
				return true;
			}
		} else {
			if (index(attribute) < 0) {
				String name = entity.name(entity.index(attribute));
				if (entity.unregister(attribute)) {
					register(name, attribute);
					attribute.setEntity(this);
					return true;
				} else {
					return false;
				}
			} else {
				if (entity.unregister(attribute)) {
					return true;
				} else {
					throw new IllegalStateException();
				}
			}
		}
	}

	final public boolean unregister(AbstractAttribute attribute) {
		int index = index(attribute);
		if (index >= 0) {
			attr[index] = null;
			attribute.setEntity(null);
		}
		return false;
	}

	public void register(String key, AbstractAttribute attribute) {
		int index = aggregate.index(key);
		attr[index] = attribute;
	}

	public Set<Entry<String,String>> entrySet() {
		return new AbstractSet<Entry<String,String>>() {

			public boolean contains(Object o) {
				if (o == null) {
					return false;
				} else {
					if (o instanceof AbstractAttribute && index((AbstractAttribute) o) >= 0) {
						return true;
					}
					if (o instanceof Entry<?,?>) {
						String name = (String) ((Entry<?,?>) o).getKey();
						String value = (String) ((Entry<?,?>) o).getValue();
						int index = aggregate.index(name);
						if (index < 0) {
							return false;
						} else {
							return value.equals(((Attribute) attr[index]).getValue());
						}
					} else {
						return false;
					}
				}
			}

			public Iterator<Entry<String,String>> iterator() {
				return new AbstractIterator<Entry<String,String>>(this) {
					private int index = 0;

					public boolean hasNext() {
						if (index < attr.length) {
							if (attr[index] == null) {
								++index;
								return hasNext();
							} else {
								return true;
							}
						} else {
							return false;
						}
					}

					@SuppressWarnings("unchecked")
					public Attribute getNext() {
						if (hasNext()) {
							return (Attribute) attr[index];
						} else {
							return null;
						}
					}

				};
			}

			public int size() {
				int result = 0;
				for (int i =0; i<attr.length; ++i) {
					if (attr[i] != null) {
						++result;
					}
				}
				return result;
			}

			@Override
			public boolean add(Entry<String,String> e) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean remove(Object o) {
				// TODO Auto-generated method stub
				return false;
			}

		};
	}

}
