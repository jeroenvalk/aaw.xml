package nl.agentsatwork.attribute;

import static org.junit.Assert.*;

import nl.agentsatwork.aggregates.Entity;

import org.junit.Before;
import org.junit.Test;

public class AbstractIndependentAttributeTest extends AbstractAttributeTest {

	@Before
	public void setUp() {
		super.setUp();
		attribute = new AbstractIndependentAttribute("testing") {

			public String defaultValue() {
				return null;
			}
			
		};
		entity = (Entity) attribute;
		
	}

	@Test
	public void testGetLocation() {
		assertEquals("testing",attribute.getKey());
	}

}
