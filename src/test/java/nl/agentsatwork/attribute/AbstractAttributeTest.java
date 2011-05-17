package nl.agentsatwork.attribute;

import static org.junit.Assert.*;

import nl.agentsatwork.aggregates.Entity;
import nl.agentsatwork.aggregates.Location;

import org.junit.Before;
import org.junit.Test;

public class AbstractAttributeTest extends AbstractImmutableAttributeTest {

	@Before
	public void setUp() {
		super.setUp();
		attribute = new AbstractAttribute() {
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
	public void testSetValue() {
		attribute.setValue("value");
		assertEquals("value", attribute.getValue());
	}

}
