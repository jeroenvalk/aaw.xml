package nl.agentsatwork.attribute;

import nl.agentsatwork.aggregates.Aggregate;
import nl.agentsatwork.aggregates.AggregateIndex;
import nl.agentsatwork.aggregates.IndependentLocation;
import nl.agentsatwork.aggregates.Location;

abstract public class AbstractIndependentAttribute extends AbstractAttribute {

	final private Location location;

	private class AttributeAggregate implements Aggregate {

		final private String key;

		public AttributeAggregate(String key) {
			this.key = key;
		}
		
		public AggregateIndex getIndex() {
			return null;
		}

		public int enter(Object entity) {
			return -1;
		}

		public boolean leave(int position) {
			return false;
		}
		
		public String getKey() {
			return key;
		}
	}
	
	public AbstractIndependentAttribute(final String key) {
		location = new IndependentLocation();
		location.setAggregate(new AttributeAggregate(key));
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
