package nl.agentsatwork.attribute;

import nl.agentsatwork.attributes.Entity;

public class AbstractIndependentAttribute extends AbstractAttribute {

	private Entity superior;

	public AbstractIndependentAttribute(String key) {
		super(key);
		superior = defaultEntity;
	}

	final public Entity getSuperior() {
		return superior;
	}

	final public void setEntity(Entity superior) {
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
