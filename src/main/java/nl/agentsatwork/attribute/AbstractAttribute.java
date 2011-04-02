package nl.agentsatwork.attribute;

public class AbstractAttribute {

	final static public DefaultEntity defaultEntity = new DefaultEntityImpl();

	public AbstractAttribute(String key) {
		defaultEntity.register(key, this);
	}

	public nl.agentsatwork.attributes.Entity getSuperior() {
		return null;
	}

	public void setEntity(nl.agentsatwork.attributes.Entity superior) {
		if (superior != null) {
			superior.register(this);
		}
	}

}
