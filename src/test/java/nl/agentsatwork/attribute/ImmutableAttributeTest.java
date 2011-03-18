package nl.agentsatwork.attribute;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.util.Set;

import nl.agentsatwork.attributes.AbstractSuperior;
import nl.agentsatwork.attributes.Superior;

import org.junit.Before;
import org.junit.Test;

public class ImmutableAttributeTest {

	protected Superior superior = null;
	protected ImmutableAttribute attribute = null;

	@Before
	public void setUp() {
		superior = new AbstractSuperior() {
			public String valueOf(String name) {
				return null;
			}			
		};
		attribute = new ImmutableAttribute(superior, "test") {
			protected String defaultValue() {
				return "test";
			}
		};
	}
	
	@Test
	public void testGetSuperior() {
		// test superior set by constructor
		assertSame(superior, attribute.getSuperior());
		
		// test disable superior
		attribute.setSuperior(null);
		assertNull(attribute.getSuperior());
		attribute.setSuperior(null);
		assertNull(attribute.getSuperior());

		// test superior set by setSuperior
		attribute.setSuperior(superior);
		assertSame(superior, attribute.getSuperior());
		
	}

	@Test
	public void testSetSuperior() {
		Superior superior1 = new Superior() {

			public Set<String> attributes() {
				return null;
			}

			public Attribute attribute(String key) {
				return null;
			}

			public boolean register(Attribute attribute) {
				return false;
			}

			public boolean unregister(Attribute attribute) {
				return false;
			}

			public String valueOf(String name) {
				return null;
			}
			
		};
		Superior superior2 = new Superior() {

			public Set<String> attributes() {
				return null;
			}

			public Attribute attribute(String key) {
				return null;
			}

			public boolean register(Attribute attribute) {
				return true;
			}

			public boolean unregister(Attribute attribute) {
				return false;
			}

			public String valueOf(String name) {
				return null;
			}
			
		};

		// test if registration to superior1 fails
		attribute.setSuperior(superior1);
		assertNull(attribute.getSuperior());

		// register to superior2
		attribute.setSuperior(superior2);
		assertSame(superior2, attribute.getSuperior());
		
		// test if unregistration of superior2 fails
		attribute.setSuperior(null);
		assertSame(superior2, attribute.getSuperior());
		attribute.setSuperior(superior);
		assertSame(superior2, attribute.getSuperior());
		attribute.setSuperior(superior1);
		assertSame(superior2, attribute.getSuperior());
	}

	@Test
	public void testGetKey() {
		assertEquals("test", attribute.getKey());
		assertSame(attribute.getKey(), attribute.getKey());
	}

	@Test
	public void testGetValue() {
		assertEquals("test", attribute.getValue());
		attribute.setSuperior(new AbstractSuperior() {
			public String valueOf(String name) {
				return "test2";
			}
		});
		assertEquals("test2", attribute.getValue());
	}
	
	@Test
	public void testSetValue() {
		try {
			attribute.setValue(null);
			fail("Operation should not be supported");
		} catch(UnsupportedOperationException e) {
			
		}
	}

}
