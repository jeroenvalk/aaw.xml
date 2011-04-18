package nl.agentsatwork.attribute;

import nl.agentsatwork.aggregates.AbstractEntity;
import nl.agentsatwork.aggregates.Location;

abstract public class AbstractImmutableAttribute extends AbstractEntity implements Attribute {

	final public String getKey() {
		Location location = getLocation();
		if (location instanceof AttributeLocation) {
			return ((AttributeLocation) location).getKey();
		} else {
			return location.toString();
		}
	}
	
	public String getValue() {
		return defaultValue();
	}
	
	public String setValue(String value) {
		throw new UnsupportedOperationException();
	}

}
