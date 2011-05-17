package nl.agentsatwork.aggregates;

import static org.junit.Assert.*;

import org.junit.Test;

public class IndependentLocationTest {

	protected Location location = new IndependentLocation();
	private Aggregate aggregate = new AbstractAggregate() {

		public AggregateIndex getIndex() {
			// TODO Auto-generated method stub
			return null;
		}

		public Location getLocation() {
			// TODO Auto-generated method stub
			return null;
		}
		
	};
	
	@Test
	public void testGetAggregate() {
		assertNull(location.getAggregate());
	}

	@Test
	public void testSetAggregate() {
		location.setAggregate(aggregate);
		assertNotNull(location.getAggregate());
		assertSame(aggregate, location.getAggregate());
	}

	@Test
	public void testGetPosition() {
		assertEquals(-1, location.getPosition());
	}

	@Test
	public void testSetPosition() {
		location.setPosition(0);
		assertEquals(0, location.getPosition());
	}

}
