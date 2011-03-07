package nl.agentsatwork.attribute;

import nl.agentsatwork.attributes.Superior;

abstract public class MutableAttribute extends ImmutableAttribute {

	protected String value = null;

	public MutableAttribute(Superior superior, String key) {
		super(superior, key);
	}

	final public String setValue(String value) {
		String result = getValue();
		this.value = value;
		return result;
	}

	public String getValue() {
		if (value == null) {
			return super.getValue();
		} else {
			return value;
		}		
	}

}
