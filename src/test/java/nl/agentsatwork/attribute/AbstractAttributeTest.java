package nl.agentsatwork.attribute;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class AbstractAttributeTest {
	static private Logger logger = Logger.getLogger(AbstractAttributeTest.class);
	protected AbstractImmutableAttribute attribute = null;

	@Before
	public void setUp() {
		attribute = new AbstractImmutableAttribute("test");
	}

	@Test
	public void testAbstractAttribute() {
		Entity entity = AbstractImmutableAttribute.defaultEntity;
		int index = entity.index(attribute);
		assertFalse(index < 0);
		assertEquals("test", entity.name(index));
		assertSame(attribute, entity.attribute(index));
	}

	@Test
	public void testGetSuperior() {
		assertNull(attribute.getSuperior());
	}

	@Test
	public void testSetSuperior() {
		Entity entity = new DefaultEntityImpl();
		attribute.setEntity(entity);

		int index = entity.index(attribute);
		assertFalse(index < 0);
		assertEquals("test", entity.name(index));
		assertSame(attribute, entity.attribute(index));

	}

}
