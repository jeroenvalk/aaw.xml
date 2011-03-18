package nl.agentsatwork.attribute;

import nl.agentsatwork.attributes.Superior;

abstract public class ImmutableAttribute implements Attribute {

	private Superior superior;
	final private String key;
	
	public ImmutableAttribute(Superior superior, String key) {
		setSuperior(superior);
		this.key = key;
	}
	
	final public Superior getSuperior() {
		return superior;
	}

	final public void setSuperior(Superior superior) {
		if (this.superior == null) {
			if (superior.register(this)) {
				this.superior = superior;
			}
		} else {
			if (this.superior.unregister(this)) {
				if (superior.register(this)) {
					this.superior = superior;
				} else {
					this.superior = null;
				}
			}
		}
	}
	
	final public String getKey() {
		return key;
	}

	public String setValue(String value) {
		throw new UnsupportedOperationException();
	}

	public String getValue() {
		String result = superior.valueOf(key);
		if (result == null) {
			return defaultValue();
		} else {
			return result;
		}
	}

	abstract protected String defaultValue();

}
