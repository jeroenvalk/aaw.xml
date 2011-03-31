package nl.agentsatwork.attribute;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AbstractAttributeTest {
	protected AbstractAttribute attribute = null;

	@Before
	public void setUp() {
		attribute = new AbstractAttribute("test");
	}

	@Test
	public void testAbstractAttribute() {
		Superior superior = AbstractAttribute.defaultSuperior;
		assertTrue(superior.index(attribute) == 0);
		assertEquals("test", superior.name(0));
		assertSame(attribute, superior.attribute(0));
	}

	@Test
	public void testGetSuperior() {
		assertNull(attribute.getSuperior());
	}

	@Test
	public void testSetSuperior() {
		Superior superior = new DefaultSuperior();
		attribute.setSuperior(superior);

		assertTrue(superior.index(attribute) == 0);
		assertEquals("test", superior.name(0));
		assertSame(attribute, superior.attribute(0));

		superior = AbstractAttribute.defaultSuperior;
		assertTrue(superior.index(attribute) == -1);
	}

}
