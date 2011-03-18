package nl.agentsatwork.attribute;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MutableAttributeTest extends ImmutableAttributeTest {

	@Before
	public void setUp() {
		super.setUp();
		attribute = new MutableAttribute(superior, "test") {
			@Override
			protected String defaultValue() {
				return "test";
			}	
		};
	}

	@Test
	public void testSetValue() {
		assertEquals("test", attribute.setValue("test3"));
	}

	@Test
	public void testGetValue() {
		attribute.setValue("test3");
		assertEquals("test3", attribute.getValue());
		attribute.setValue(null);
		super.testGetValue();
		assertEquals("test2", attribute.setValue("test3"));
		attribute.setValue(null);
		assertEquals("test2", attribute.getValue());
	}

}
