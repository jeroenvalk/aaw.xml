package nl.agentsatwork.attribute;

import nl.agentsatwork.attributes.Superior;

public class AbstractIndependentAttribute extends AbstractAttribute {

	private Superior superior;

	public AbstractIndependentAttribute(String key) {
		super(key);
		superior = defaultSuperior;
	}

	final public Superior getSuperior() {
		return superior;
	}

	final public void setSuperior(Superior superior) {
		if (superior == null) {
			if (this.superior != null && this.superior.unregister(this)) {
				this.superior = null;
			}
		} else {
			if (superior.register(this)) {
				this.superior = superior;
			}
		}
	}
}
