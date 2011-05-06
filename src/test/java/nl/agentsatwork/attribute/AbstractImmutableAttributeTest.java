package nl.agentsatwork.attribute;

import static org.junit.Assert.*;

import nl.agentsatwork.aggregates.AbstractEntityTest;

import org.junit.Test;

public class AbstractImmutableAttributeTest extends AbstractEntityTest {

	protected Attribute attribute = null;
	
	@Test
	public void testGetKey() {
		assertEquals("testkey", attribute.getKey());
	}

	@Test
	public void testGetValue() {
		assertEquals(attribute.defaultValue(), attribute.getKey());
	}

	@Test
	public void testSetValue() {
		try {
			attribute.setValue("value");
			fail("cannot set value");
		} catch(UnsupportedOperationException e) {
			
		}
	}

}
