package nl.agentsatwork.attributes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class AbstractSuperiorTest {
	protected Entity superior = null;

	@Before
	public void setUp() throws Exception {
		superior = new AbstractEntity() {

			public String valueOf(String name) {
				return name;
			}
			
		};
	}

	@Test
	public void testAttributes() {
		Set<String> names = superior.attributes();
		assertTrue(isImmutable(names));
		assertTrue(names.isEmpty());
	}

	@Test
	public void testAttribute() {
		assertNull(superior.attribute(null));
		assertNull(superior.attribute(""));
		assertNull(superior.attribute("test"));
	}

	@Test
	public void testRegister() {
		// cannot register null
		try {
			superior.register(null);
			fail("cannot register null");
		} catch (NullPointerException e) {

		}

		// normal registration
		ImmutableAttribute attribute = new MutableAttribute(superior, "test") {
			@Override
			protected String defaultValue() {
				return "test";
			}
		};
		assertTrue(superior.register(attribute));
		
		// double registration fails
		assertFalse(superior.register(attribute));
		
		// registration sets the superior
		assertSame(superior, attribute.getSuperior());
		
		// attribute for key already registered
		attribute = new MutableAttribute(superior, "test") {
			@Override
			protected String defaultValue() {
				return "test";
			}
		};
		assertFalse(superior.register(attribute));

		// already registered elsewhere
		attribute = new MutableAttribute(superior, "test2") {
			@Override
			protected String defaultValue() {
				return "test";
			}
		};
		Entity superior1 = new AbstractEntity() {

			public String valueOf(String name) {
				return null;
			}
			
		};
		assertTrue(superior1.register(attribute));
		assertFalse(superior.register(attribute));
	}

	@Test
	public void testUnregister() {
		// cannot unregister null
		try {
			superior.unregister(null);
			fail("cannot unregister null");
		} catch (NullPointerException e) {

		}

		// normal unregistration
		ImmutableAttribute attribute = new MutableAttribute(superior, "test") {
			@Override
			protected String defaultValue() {
				return "test";
			}
		};
		attribute.setEntity(superior);
		assertTrue(superior.unregister(attribute));
		assertNull(attribute.getSuperior());

		// cannot unregister what is not registered
		assertFalse(superior.unregister(attribute));

		// unregistration is NOT done by key
		attribute.setEntity(superior);
		attribute = new MutableAttribute(superior, "test") {
			@Override
			protected String defaultValue() {
				return "test";
			}
		};
		assertFalse(superior.unregister(attribute));
	}

	@Test
	public void testValueOf() {
		assertEquals("test", superior.valueOf("test"));
		assertEquals("test2", superior.valueOf("test2"));
	}

	private boolean isImmutable(Set<?> set) {
		boolean result = true;
		try {
			set.add(null);
			result = false;
		} catch (UnsupportedOperationException e) {

		}
		try {
			set.addAll(null);
			result = false;
		} catch (UnsupportedOperationException e) {

		}
		try {
			set.clear();
			result = false;
		} catch (UnsupportedOperationException e) {

		}
		try {
			set.remove(null);
			result = false;
		} catch (UnsupportedOperationException e) {

		}
		try {
			set.removeAll(null);
			result = false;
		} catch (UnsupportedOperationException e) {

		}
		try {
			set.retainAll(null);
			result = false;
		} catch (UnsupportedOperationException e) {

		}
		return result;
	}
}
