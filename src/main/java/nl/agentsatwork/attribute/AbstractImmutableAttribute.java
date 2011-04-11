package nl.agentsatwork.attribute;

import nl.agentsatwork.aggregates.AbstractEntity;
import nl.agentsatwork.aggregates.Aggregate;

abstract public class AbstractImmutableAttribute extends AbstractEntity implements Attribute {

	final public String getKey() {
		Aggregate aggregate = getAggregate();
		int index = aggregate.indexOf(this);
		assert index >= 0;
		if (aggregate instanceof AttributeNames) {
			return ((AttributeNames) aggregate).nameOf(index);
		} else {
			return Integer.toString(index);
		}
	}
	
	public String getValue() {
		return defaultValue();
	}
	
	public String setValue(String value) {
		throw new UnsupportedOperationException();
	}

}
