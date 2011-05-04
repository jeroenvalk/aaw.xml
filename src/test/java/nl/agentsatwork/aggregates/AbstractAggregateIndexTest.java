package nl.agentsatwork.aggregates;

import static org.junit.Assert.*;

import org.junit.Test;

public class AbstractAggregateIndexTest extends AbstractAggregateTest {

	@Test
	public void testGetIndex() {
		assertSame(aggregate, aggregate.getIndex());
	}

	@Test
	public void testOffset() {
		AggregateIndex index = (AggregateIndex) aggregate;
		assertEquals(0, index.offset());
	}

	@Test
	public void testLimit() {
		AggregateIndex index = (AggregateIndex) aggregate;
		assertEquals(0, index.limit());
	}

	@Test
	public void testValueOf() {
		fail("Not yet implemented");
	}

	@Test
	public void testSize() {
		AggregateIndex index = (AggregateIndex) aggregate;
		assertEquals(0, index.size());
	}

	@Test
	public void testAutonumerical() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

}
