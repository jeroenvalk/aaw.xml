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
		assertSame(AbstractImmutableAttribute.defaultEntity, attribute.getSuperior());
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
		assertSame(AbstractImmutableAttribute.defaultEntity, attribute.getSuperior());
		super.testSetSuperior();
		nl.agentsatwork.attributes.Entity superior = AbstractImmutableAttribute.defaultEntity;
		assertTrue(superior.index(attribute) == -1);

		assertNotNull(attribute.getSuperior());
		assertNotSame(AbstractImmutableAttribute.defaultEntity,
				attribute.getSuperior());

		Entity superior1 = new Entity() {

			public int index(AbstractImmutableAttribute attribute) {
				return 0;
			}

			public int index(String name) {
				return 0;
			}

			public String name(int index) {
				return null;
			}

			public AbstractImmutableAttribute attribute(int index) {
				return null;
			}

			public boolean register(AbstractImmutableAttribute abstractAttribute) {
				return false;
			}

			public boolean unregister(AbstractImmutableAttribute attribute) {
				return false;
			}

			public void register(String key, AbstractImmutableAttribute attribute) {
				// TODO Auto-generated method stub
				
			}
			
		};
		Entity superior2 = new Entity() {

			public int index(AbstractImmutableAttribute attribute) {
				return 0;
			}

			public int index(String name) {
				return 0;
			}

			public String name(int index) {
				return null;
			}

			public AbstractImmutableAttribute attribute(int index) {
				return null;
			}

			public boolean register(AbstractImmutableAttribute abstractAttribute) {
				return true;
			}

			public boolean unregister(AbstractImmutableAttribute attribute) {
				return false;
			}

			public void register(String key, AbstractImmutableAttribute attribute) {
				// TODO Auto-generated method stub
				
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
		attribute.setEntity(AbstractImmutableAttribute.defaultEntity);
		assertSame(superior2, attribute.getSuperior());
		attribute.setEntity(superior1);
		assertSame(superior2, attribute.getSuperior());
		
	}

}
