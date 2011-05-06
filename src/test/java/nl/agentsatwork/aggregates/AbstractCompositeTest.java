package nl.agentsatwork.aggregates;

import static org.junit.Assert.*;

import org.junit.Test;

public class AbstractCompositeTest {

	private Composite<Integer> composite = null; 

	@Test
	public void testI() {
		try {
			composite.i(0);
			fail("composite must be empty");
		} catch(IndexOutOfBoundsException e) {
			
		}
		composite.resize(1);
		assertEquals(0, composite.getIndex(0).enter(1));
		assertEquals(0, composite.i(0));
		assertEquals(0, composite.j(0));
		assertEquals(0, composite.k(0, 0));
	}

	@Test
	public void testJ() {
		try {
			composite.j(0);
			fail("composite must be empty");
		} catch(IndexOutOfBoundsException e) {
			
		}
	}

	@Test
	public void testK() {
		try {
			composite.k(0, 0);
			fail("composite must be empty");
		} catch(IndexOutOfBoundsException e) {
			
		}
	}

	@Test
	public void testGetIndex() {
		try {
			composite.getIndex(0);
			fail("composite must be empty");
		} catch(IndexOutOfBoundsException e) {
			
		}
	}

	@Test
	public void testGetValue() {
		try {
			composite.getValue(0);
			fail("composite must be empty");
		} catch(IndexOutOfBoundsException e) {
			
		}
	}

	@Test
	public void testSize() {
		assertEquals(0, composite.size());
	}

	@Test
	public void testResize() {
		composite.resize(1);
		assertEquals(0, composite.size());
		assertEquals(0, composite.getIndex(0).size());
	}

}
