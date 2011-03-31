package nl.agentsatwork.attribute;

import static org.junit.Assert.*;

import java.util.Set;

import nl.agentsatwork.attributes.Superior;

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
		assertSame(AbstractAttribute.defaultSuperior, attribute.getSuperior());
	}

	@Test
	public void testGetSuperior() {
		nl.agentsatwork.attributes.Superior superior = attribute.getSuperior();
		assertNotNull(superior);

		// test disable superior
		attribute.setSuperior(null);
		assertNull(attribute.getSuperior());
		assertTrue(superior.index(attribute) == -1);
		attribute.setSuperior(null);
		assertNull(attribute.getSuperior());
		assertTrue(superior.index(attribute) == -1);

		// test superior set by setSuperior
		attribute.setSuperior(superior);
		assertSame(superior, attribute.getSuperior());
		assertTrue(superior.index(attribute) == 1);
	}

	@Test
	public void testSetSuperior() {
		assertSame(AbstractAttribute.defaultSuperior, attribute.getSuperior());
		super.testSetSuperior();
		assertNotNull(attribute.getSuperior());
		assertNotSame(AbstractAttribute.defaultSuperior,
				attribute.getSuperior());

		Superior superior1 = new nl.agentsatwork.attributes.Superior() {

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
		Superior superior2 = new nl.agentsatwork.attributes.Superior() {

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
		assertSame(AbstractAttribute.defaultSuperior, attribute.getSuperior());
		attribute.setSuperior(superior1);
		assertSame(AbstractAttribute.defaultSuperior, attribute.getSuperior());

		// register to superior2
		attribute.setSuperior(superior2);
		assertSame(superior2, attribute.getSuperior());
		
		// test if unregistration of superior2 fails
		attribute.setSuperior(null);
		assertSame(superior2, attribute.getSuperior());
		attribute.setSuperior(AbstractAttribute.defaultSuperior);
		assertSame(superior2, attribute.getSuperior());
		attribute.setSuperior(superior1);
		assertSame(superior2, attribute.getSuperior());
		
	}

}
