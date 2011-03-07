package nl.agentsatwork.attribute;

import nl.agentsatwork.attributes.Superior;

final public class DefaultAttribute extends AbstractAttribute {

	public DefaultAttribute(Superior superior, String key) {
		super(superior, key);
	}

	final public String getValue() {
		if (value == null) {
			return getSuperior().valueOf(getKey());
		} else {
			return value;
		}		
	}

}
