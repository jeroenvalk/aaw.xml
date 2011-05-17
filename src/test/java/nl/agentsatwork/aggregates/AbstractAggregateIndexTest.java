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
		assertEquals(0, index.autonumerical(0));
		assertEquals(1, index.autonumerical(1));
		assertEquals(2, index.autonumerical(2));
		index.remove(0);
		assertEquals(1, index.offset());
		index.remove(2);
		assertEquals(1, index.offset());
		index.remove(1);
		assertEquals(3, index.offset());
	}

	@Test
	public void testLimit() {
		AggregateIndex index = (AggregateIndex) aggregate;
		assertEquals(0, index.limit());
		assertEquals(0, index.autonumerical(0));
		assertEquals(1, index.limit());
		assertEquals(1, index.autonumerical(1));
		assertEquals(2, index.limit());
		index.remove(0);
		assertEquals(2, index.limit());
		index.remove(1);
		assertEquals(2, index.limit());
	}

	@Test
	public void testValueOf() {
		AggregateIndex index = (AggregateIndex) aggregate;
		Integer a = 0, b = 1;
		assertNull(index.valueOf(-1));
		assertNull(index.valueOf(-2));
		assertEquals(0, index.autonumerical(a));
		assertEquals(1, index.autonumerical(b));
		assertSame(a, index.valueOf(0));
		assertSame(b, index.valueOf(1));
		assertNull(index.valueOf(2));
	}

	@Test
	public void testSize() {
		AggregateIndex index = (AggregateIndex) aggregate;
		assertEquals(0, index.size());
		index.autonumerical(0);
		index.autonumerical(1);
		assertEquals(2, index.size());
		index.remove(1);
		assertEquals(1, index.size());
		index.autonumerical(2);
		assertEquals(2, index.size());
		index.autonumerical(3);
		assertEquals(3, index.size());
	}

	@Test
	public void testAutonumerical() {
		AggregateIndex index = (AggregateIndex) aggregate;
		assertEquals(0, index.autonumerical(null));
		assertEquals(1, index.offset());
		assertEquals(1, index.limit());
		
		assertEquals(1, index.autonumerical(1));
		assertEquals(2, index.autonumerical(2));
		assertEquals(3, index.autonumerical(3));
		index.remove(3);
		assertEquals(4, index.autonumerical(3));
	}

	@Test
	public void testRemove() {
		AggregateIndex index = (AggregateIndex) aggregate;
		assertEquals(0, index.autonumerical(0));
		assertNotNull(index.valueOf(0));
		index.remove(0);
		assertNull(index.valueOf(0));
		assertEquals(1, index.offset());
		assertEquals(1, index.limit());
	}

}
