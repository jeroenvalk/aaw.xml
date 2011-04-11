package nl.agentsatwork.attribute;

abstract public class AbstractAttribute extends AbstractImmutableAttribute {

	String value = null;
	
	public String getValue() {
		if (value == null) {
			return defaultValue();
		} else {
			return value;
		}
	}
	
	public String setValue(String value) {
		String result = getValue();
		this.value = value;
		return result;
	}
	
}
