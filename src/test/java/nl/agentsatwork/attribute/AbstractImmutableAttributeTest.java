package nl.agentsatwork.attribute;

import static org.junit.Assert.*;

import nl.agentsatwork.aggregates.AbstractEntityTest;
import nl.agentsatwork.aggregates.Entity;
import nl.agentsatwork.aggregates.Location;

import org.junit.Before;
import org.junit.Test;

public class AbstractImmutableAttributeTest extends AbstractEntityTest {

	protected Attribute attribute = null;

	@Before
	public void setUp() {
		super.setUp();
		attribute = new AbstractImmutableAttribute() {
			final private AttributeLocation location = new IndependentAttributeLocation("testing");

			public String defaultValue() {
				return null;
			}

			public Location getLocation() {
				return location;
			}
			
		};
		entity = (Entity) attribute;
		
	}
	
	@Test
	public void testGetKey() {
		assertEquals("testing", attribute.getKey());
	}

	@Test
	public void testGetValue() {
		assertEquals(attribute.defaultValue(), attribute.getValue());
	}

	@Test
	public void testSetValue() {
		try {
			attribute.setValue("value");
			fail("cannot set value");
		} catch(UnsupportedOperationException e) {
			
		}
	}

}
