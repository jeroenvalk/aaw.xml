package nl.agentsatwork.attribute;

abstract public class MutableAttribute extends ImmutableAttribute {

	protected String value = null;

	public MutableAttribute(String key) {
		super(key);
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
