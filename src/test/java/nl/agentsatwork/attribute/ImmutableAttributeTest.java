package nl.agentsatwork.attribute;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class ImmutableAttributeTest extends AbstractIndependentAttributeTest {

	protected Entity superior = null;

	@Before
	public void setUp() {
		superior = AbstractImmutableAttribute.defaultEntity;
		attribute = new ImmutableAttribute("test") {
			public String defaultValue() {
				return "test";
			}
		};
	}
	
	@Test
	public void testGetKey() {
		assertEquals("test", ((Attribute) attribute).getKey());
	}

	@Test
	public void testGetValue() {
		assertEquals("test", ((Attribute) attribute).getValue());
	}
	
	@Test
	public void testSetValue() {
		try {
			((Attribute) attribute).setValue(null);
			fail("Operation should not be supported");
		} catch(UnsupportedOperationException e) {
			
		}
	}

}
