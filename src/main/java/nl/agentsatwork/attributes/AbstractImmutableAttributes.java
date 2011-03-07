package nl.agentsatwork.attributes;

import nl.agentsatwork.attribute.Attribute;

abstract public class AbstractImmutableAttributes extends AbstractSuperior
		implements MutableAttributes {

	public AbstractImmutableAttributes() {
		Attribute[] attribute = attributesOf();
		for (int i = 0; i < attribute.length; ++i) {
			register(attribute[i]);
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

	abstract Attribute[] attributesOf();

}
