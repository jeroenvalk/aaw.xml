package nl.agentsatwork.attribute;

import nl.agentsatwork.attributes.Superior;

abstract public class AbstractAttribute extends AbstractImmutableAttribute {

	protected String value = null;

	public AbstractAttribute(Superior superior, String key) {
		super(superior, key);
	}

	final public String setValue(String value) {
		String result = getValue();
		this.value = value;
		return result;
	}
	
}
