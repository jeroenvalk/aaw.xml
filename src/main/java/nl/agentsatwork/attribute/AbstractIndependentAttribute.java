package nl.agentsatwork.attribute;

import nl.agentsatwork.attributes.Entity;

public class AbstractIndependentAttribute extends AbstractImmutableAttribute {

	private Entity entity;

	public AbstractIndependentAttribute(String key) {
		super(key);
		entity = defaultEntity;
	}

	final public Entity getSuperior() {
		return entity;
	}

	final public void setEntity(Entity superior) {
		if (superior == null) {
			if (this.entity != null && this.entity.unregister(this)) {
				this.entity = null;
			}
		} else {
			if (superior.register(this)) {
				this.entity = superior;
			}
		}
	}
}
