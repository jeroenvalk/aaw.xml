package nl.agentsatwork.attribute;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
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
		Superior superior = attribute.getSuperior();
		
		assertNotNull(attribute.getSuperior());
		assertSame(attribute.getSuperior(), attribute.getSuperior());
		
		attribute.setSuperior(null);
		assertNull(attribute.getSuperior());
		
		attribute.setSuperior(superior);
		assertSame(superior, attribute.getSuperior());
		
		superior = new AbstractSuperior() {
			public String valueOf(String name) {
				return "test";
			}
		};
		attribute.setSuperior(superior);
		assertSame(superior, attribute.getSuperior());
	}

	@Test
	public void testSetSuperior() {
		Superior superior = attribute.getSuperior();
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
		
		assertNotNull(superior);
		
		attribute.setSuperior(null);
		assertNull(attribute.getSuperior());
		attribute.setSuperior(null);
		assertNull(attribute.getSuperior());
		
		attribute.setSuperior(superior1);
		assertNull(attribute.getSuperior());
		
		attribute.setSuperior(superior);
		assertSame(superior, attribute.getSuperior());
		
		attribute.setSuperior(superior1);
		assertNull(attribute.getSuperior());
		
		attribute.setSuperior(superior);
		attribute.setSuperior(superior2);
		assertSame(superior2, attribute.getSuperior());
		
		attribute.setSuperior(null);
		assertSame(superior2, attribute.getSuperior());
		attribute.setSuperior(superior);
		assertSame(superior2, attribute.getSuperior());
		attribute.setSuperior(superior1);
		assertSame(superior2, attribute.getSuperior());
	}

	@Test
	public void testGetKey() {
		assertNotNull(attribute.getKey());
		assertFalse(attribute.getKey().equals(""));
		assertSame(attribute.getKey(), attribute.getKey());
	}

	@Test
	public void testGetValue() {
		attribute.setSuperior(new AbstractSuperior() {
			public String valueOf(String name) {
				return "test";
			}
		});
		assertEquals("test", attribute.getValue());
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
