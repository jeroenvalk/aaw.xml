package nl.agentsatwork.attribute;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AbstractIndependentAttributeTest extends AbstractAttributeTest {

	@Before
	public void setUp() {
		attribute = new AbstractIndependentAttribute("testing") {

			public String defaultValue() {
				return null;
			}
			
		};
		
	}
	@Test
	public void testGetLocation() {
		assertEquals("testing",attribute.getKey());
	}

}
