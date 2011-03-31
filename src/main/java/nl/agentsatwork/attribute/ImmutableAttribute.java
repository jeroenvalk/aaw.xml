package nl.agentsatwork.attribute;

abstract public class ImmutableAttribute extends AbstractIndependentAttribute implements
		Attribute {

	public ImmutableAttribute(String key) {
		super(key);
	}

	final public String getKey() {
		nl.agentsatwork.attributes.Superior superior = getSuperior();
		return superior.name(superior.index(this));
	}

	public String setValue(String value) {
		throw new UnsupportedOperationException();
	}

	public String getValue() {
		return defaultValue();
	}

}
