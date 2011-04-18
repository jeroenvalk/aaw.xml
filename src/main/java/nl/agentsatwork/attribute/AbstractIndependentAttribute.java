package nl.agentsatwork.attribute;

import nl.agentsatwork.aggregates.Aggregate;
import nl.agentsatwork.aggregates.IndependentLocation;
import nl.agentsatwork.aggregates.Location;

abstract public class AbstractIndependentAttribute extends AbstractAttribute {

	final private Location location;

	private abstract class AttributeAggregate implements Aggregate {
		abstract public String getKey();
	}
	
	public AbstractIndependentAttribute(final String key) {
		location = new IndependentLocation();
		location.setAggregate(new AttributeAggregate() {

			public int enter(Object entity) {
				return -1;
			}

			public boolean leave(int position) {
				return false;
			}

			@Override
			public String getKey() {
				return key;
			}
			
		});
	}

	public Aggregate getAggregate() {
		return location.getAggregate();
	}

	public int getPosition() {
		return location.getPosition();
	}

	public void setAggregate(Aggregate aggregate) {
		location.setAggregate(aggregate);
	}

	public void setPosition(int position) {
		location.setPosition(position);
	}

}
