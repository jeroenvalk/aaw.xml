package nl.agentsatwork.aggregates;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class AbstractEntityTest {

	protected Entity entity = null;
	protected AggregateIndex aggregate = null;

	@Before
	public void setUp() {
		entity = new AbstractEntity() {
			private Location location = new IndependentLocation();

			public Location getLocation() {
				return location;
			}

		};

		aggregate = new AbstractAggregateIndex() {

			public Location getLocation() {
				return null;
			}

			public int indexOf(Object value) {
				if (value == null) {
					throw new IllegalArgumentException();
				}
				for (int i = offset(); i < limit(); ++i) {
					if (value.equals(valueOf(i))) {
						return i;
					}
				}
				return -1;
			}

		};
	}

	@Test
	public void testGetLocation() {
		assertNull(entity.getLocation().getAggregate());
		assertEquals(-1, entity.getLocation().getPosition());

		assertEquals(0, entity.enter(aggregate));
		
		assertSame(aggregate, entity.getLocation().getAggregate());
		assertEquals(0, entity.getLocation().getPosition());
		
		assertTrue(entity.leave(aggregate));
		
		assertNull(entity.getLocation().getAggregate());
		assertEquals(-1, entity.getLocation().getPosition());

	}

	@Test
	public void testEnter() {
		try {
			entity.enter(null);
			fail("cannot enter null");
		} catch (IllegalArgumentException e) {

		}

		assertEquals(0, aggregate.getIndex().size());
		assertEquals(0, entity.enter(aggregate));
		assertEquals(0, entity.enter(aggregate));
		assertSame(aggregate, entity.getLocation().getAggregate());
		assertEquals(0, entity.getLocation().getPosition());
		assertEquals(1, aggregate.getIndex().size());

		Aggregate aggregate = new Aggregate() {

			public int enter(Object entity) {
				return 3;
			}

			public boolean leave(int position) {
				return false;
			}

			public AggregateIndex getIndex() {
				return null;
			}

			public Location getLocation() {
				return null;
			}

			public int enter(Aggregate aggregate) {
				return -1;
			}

			public boolean leave(Aggregate aggregate) {
				return true;
			}

		};

		assertEquals(3, entity.enter(aggregate));
		assertSame(aggregate, entity.getLocation().getAggregate());
		assertEquals(3, entity.getLocation().getPosition());

		assertEquals(1, this.aggregate.getIndex().size());
		assertSame(entity, this.aggregate.valueOf(0));

		assertEquals(-1, entity.enter(this.aggregate));
		assertSame(aggregate, entity.getLocation().getAggregate());
		assertEquals(3, entity.getLocation().getPosition());
	}

	@Test
	public void testLeave() {
		try {
			entity.leave(null);
			fail("cannot leave null");
		} catch (IllegalArgumentException e) {

		}

		assertTrue(entity.leave(aggregate));
		assertNull(entity.getLocation().getAggregate());
		assertEquals(-1, entity.getLocation().getPosition());

		assertEquals(0, entity.enter(aggregate));
		assertTrue(entity.leave(aggregate));
	}

}
