package nl.agentsatwork.attribute;

import static org.junit.Assert.*;

import org.junit.Test;

public class AbstractAttributeTest extends AbstractImmutableAttributeTest {

	@Test
	public void testSetValue() {
		attribute.setValue("value");
		assertEquals("value", attribute.getValue());
	}

}
