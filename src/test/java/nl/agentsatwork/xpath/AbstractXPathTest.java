package nl.agentsatwork.xpath;

import static org.junit.Assert.*;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.Tree;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class AbstractXPathTest {
	static private Logger logger = Logger.getLogger(AbstractXPathTest.class);
	AbstractXPath xpath;

	@Before
	public void setUp() throws Exception {
		xpath = new AbstractXPath();
	}

	@Test
	public void testParse() {
		Tree tree = null;
		try {
			tree = xpath.parse("hello/world");
		} catch (RecognitionException e) {
			logger.error(null, e);
			fail("RecognitionException");
		}
		logger.info(tree.getChildCount());
		logger.info(tree.toStringTree());
	}
}
