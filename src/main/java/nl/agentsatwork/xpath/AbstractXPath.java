package nl.agentsatwork.xpath;

import nl.agentsatwork.antlr.XPath;
import nl.agentsatwork.antlr.XPathLexer;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.Tree;

public class AbstractXPath {
//	final static private TLexer lexer = new TLexer();
	final static private XPathLexer lexer = new nl.agentsatwork.antlr.XPathLexer();

//	protected Tree parse(String xpath) throws RecognitionException {
//		lexer.setCharStream(new ANTLRStringStream(xpath));
//		TParser parser = new TParser(new CommonTokenStream(lexer));
//		a_return psrReturn = parser.a();
//		return (Tree) psrReturn.getTree();
//	}

	protected Tree parse(String xpath) throws RecognitionException {
		lexer.setCharStream(new ANTLRStringStream(xpath));
		XPath parser = new XPath(new CommonTokenStream(lexer));
		XPath.main_return result = parser.main();
		return (Tree) result.getTree();
	}
}
