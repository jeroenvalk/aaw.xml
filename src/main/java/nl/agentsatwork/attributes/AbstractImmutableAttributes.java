package nl.agentsatwork.attributes;

import java.util.Map;

import nl.agentsatwork.attribute.Attribute;

abstract public class AbstractImmutableAttributes extends AbstractAttributes implements Attributes {

	public AbstractImmutableAttributes(Attribute[] attr) {
		super(attr);
	}

	final public void set(String name, String value) {
		throw new UnsupportedOperationException();
	}

	public Map<String, String> attr() {
		return null;
	}

}
