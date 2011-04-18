package nl.agentsatwork.attributes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import nl.agentsatwork.attribute.Attribute;

import org.junit.Before;
import org.junit.Test;

public class ImmutableAttributesTest extends AbstractSuperiorTest {
	private ImmutableAttributes attributes = null;

	@Before
	public void setUp() {
		attributes = new ImmutableAttributes(
				new Attribute[] { new ImmutableAttribute(attributes, "test") {
					protected String defaultValue() {
						return "test";
					}
				} });
		superior = attributes;
	}

	@Test
	public void testValueOf() {
		assertNull(attributes.valueOf(null));
		assertNull(attributes.valueOf(""));
		assertNull(attributes.valueOf("test"));
	}

	@Test
	public void testHasAttribute() {
		assertFalse(attributes.hasAttribute(null));
		assertFalse(attributes.hasAttribute(""));
		assertTrue(attributes.hasAttribute("test"));
	}

	@Test
	public void testGet() {
		assertEquals("test", attributes.get("test"));
	}

	@Test
	public void testSet() {
		try {
			attributes.set(null, null);
			fail("should not be supported");
		} catch (UnsupportedOperationException e) {

		}
	}

	@Test
	public void testAttr() {
		fail("Not yet implemented");
	}

}
