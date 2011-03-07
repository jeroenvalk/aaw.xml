package nl.agentsatwork.attribute;

import nl.agentsatwork.attributes.Superior;

public abstract class AbstractImmutableAttribute implements Attribute {

	private Superior superior;
	final private String key;
	
	public AbstractImmutableAttribute(Superior superior, String key) {
		this.superior = superior;
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

}
