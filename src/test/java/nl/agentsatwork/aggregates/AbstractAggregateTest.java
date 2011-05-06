package nl.agentsatwork.aggregates;

import static org.junit.Assert.*;

import org.junit.Test;

public class AbstractAggregateTest extends AbstractEntityTest {

	@Test
	public void testEnterObject() {
		try {
			aggregate.enter(null);
			fail("cannot enter null");
		} catch (IllegalArgumentException e) {

		}
		
		assertEquals(0, aggregate.enter("test"));
		assertEquals(1, aggregate.enter(entity));
	}

	@Test
	public void testLeaveInt() {
		try {
			aggregate.leave(-1);
			fail("position must be positive");
		} catch(IllegalArgumentException e) {
			
		}
		
		assertTrue(aggregate.leave(0));
		assertEquals(0, aggregate.enter(entity));
		assertTrue(aggregate.leave(0));
		
		Entity entity = new Entity() {

			public Location getLocation() {
				return null;
			}

			public int enter(Aggregate aggregate) {
				return aggregate.getIndex().autonumerical(this);
			}

			public boolean leave(Aggregate aggregate) {
				return false;
			}
			
		};
		
		assertEquals(1, aggregate.enter(entity));
		assertFalse(aggregate.leave(1));
		
	}

}
