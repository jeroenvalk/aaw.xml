package nl.agentsatwork.attribute;

import nl.agentsatwork.attributes.Entity;

public class AbstractAttribute {

	final static public Entity defaultEntity = new DefaultEntityImpl();

	public AbstractAttribute(String key) {
		defaultEntity.register(key, this);
	}

	public Entity getSuperior() {
		return null;
	}

	public void setEntity(Entity superior) {
		if (superior != null) {
			superior.register(this);
		}
	}

}
