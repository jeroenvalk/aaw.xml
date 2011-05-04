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
		aggregate.enter(entity);
		aggregate.leave(0);
	}

}
