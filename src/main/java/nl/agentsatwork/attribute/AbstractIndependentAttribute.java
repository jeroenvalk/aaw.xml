package nl.agentsatwork.attribute;

import nl.agentsatwork.aggregates.Location;

abstract public class AbstractIndependentAttribute extends AbstractAttribute {

	final private AttributeLocation location;
	
	public AbstractIndependentAttribute(final String key) {
		location = new IndependentAttributeLocation(key);
	}

	public Location getLocation() {
		return location;
	}

}
