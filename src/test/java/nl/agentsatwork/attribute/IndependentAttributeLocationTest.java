package nl.agentsatwork.attribute;

import static org.junit.Assert.*;

import nl.agentsatwork.aggregates.IndependentLocationTest;

import org.junit.Before;
import org.junit.Test;

public class IndependentAttributeLocationTest extends IndependentLocationTest {

	@Before
	public void setUp() {
		location = new IndependentAttributeLocation("testkey");
	}
	
	@Test
	public void testGetKey() {
		assertEquals("testkey", ((AttributeLocation) location).getKey());
	}

}
