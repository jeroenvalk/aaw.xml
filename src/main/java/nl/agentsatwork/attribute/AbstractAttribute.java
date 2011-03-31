package nl.agentsatwork.attribute;

public class AbstractAttribute {

	final static public Superior defaultSuperior = new DefaultSuperior();

	public AbstractAttribute(String key) {
		defaultSuperior.register(key, this);
	}

	public nl.agentsatwork.attributes.Superior getSuperior() {
		return null;
	}

	public void setSuperior(nl.agentsatwork.attributes.Superior superior) {
		superior.register(this);
	}
	
}
