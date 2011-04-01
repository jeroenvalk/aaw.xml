package nl.agentsatwork.attribute;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MutableAttributeTest extends ImmutableAttributeTest {

	@Before
	public void setUp() {
		super.setUp();
		attribute = new MutableAttribute("test") {
			public String defaultValue() {
				return "test";
			}
		};
	}

	@Test
	public void testSetValue() {
		assertEquals("test", ((Attribute) attribute).setValue("test3"));
		assertEquals("test3", ((Attribute) attribute).getValue());
	}

	@Test
	public void testGetValue() {
		((Attribute) attribute).setValue("test3");
		assertEquals("test3", ((Attribute) attribute).getValue());
		((Attribute) attribute).setValue(null);
		super.testGetValue();
	}

}
