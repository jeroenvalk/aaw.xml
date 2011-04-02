package nl.agentsatwork.attribute;

import static org.junit.Assert.*;

import java.util.Set;

import nl.agentsatwork.attributes.Entity;

import org.junit.Before;
import org.junit.Test;

public class AbstractIndependentAttributeTest extends AbstractAttributeTest {

	@Before
	public void setUp() {
		attribute = new AbstractIndependentAttribute("test");
	}

	@Test
	public void testAbstractAttribute() {
		super.testAbstractAttribute();
		assertSame(AbstractAttribute.defaultEntity, attribute.getSuperior());
	}

	@Test
	public void testGetSuperior() {
		nl.agentsatwork.attributes.Entity superior = attribute.getSuperior();
		assertNotNull(superior);

		// test disable superior
		attribute.setEntity(null);
		assertNull(attribute.getSuperior());
		assertTrue(superior.index(attribute) == -1);
		attribute.setEntity(null);
		assertNull(attribute.getSuperior());
		assertTrue(superior.index(attribute) == -1);

		// test of null stays null
		attribute.setEntity(superior);
		assertNull(attribute.getSuperior());
	}

	@Test
	public void testSetSuperior() {
		assertSame(AbstractAttribute.defaultEntity, attribute.getSuperior());
		super.testSetSuperior();
		nl.agentsatwork.attributes.Entity superior = AbstractAttribute.defaultEntity;
		assertTrue(superior.index(attribute) == -1);

		assertNotNull(attribute.getSuperior());
		assertNotSame(AbstractAttribute.defaultEntity,
				attribute.getSuperior());

		Entity superior1 = new nl.agentsatwork.attributes.Entity() {

			public int index(AbstractAttribute attribute) {
				return 0;
			}

			public int index(String name) {
				return 0;
			}

			public String name(int index) {
				return null;
			}

			public AbstractAttribute attribute(int index) {
				return null;
			}

			public boolean register(AbstractAttribute abstractAttribute) {
				return false;
			}

			public boolean unregister(AbstractAttribute attribute) {
				return false;
			}
			
		};
		Entity superior2 = new nl.agentsatwork.attributes.Entity() {

			public int index(AbstractAttribute attribute) {
				return 0;
			}

			public int index(String name) {
				return 0;
			}

			public String name(int index) {
				return null;
			}

			public AbstractAttribute attribute(int index) {
				return null;
			}

			public boolean register(AbstractAttribute abstractAttribute) {
				return true;
			}

			public boolean unregister(AbstractAttribute attribute) {
				return false;
			}
			
		};

		// test if registration to superior1 fails
		assertNotSame(superior1, attribute.getSuperior());
		attribute.setEntity(superior1);
		assertNotSame(superior1, attribute.getSuperior());

		// register to superior2
		attribute.setEntity(superior2);
		assertSame(superior2, attribute.getSuperior());
		
		// test if unregistration of superior2 fails
		attribute.setEntity(null);
		assertSame(superior2, attribute.getSuperior());
		attribute.setEntity(AbstractAttribute.defaultEntity);
		assertSame(superior2, attribute.getSuperior());
		attribute.setEntity(superior1);
		assertSame(superior2, attribute.getSuperior());
		
	}

}
