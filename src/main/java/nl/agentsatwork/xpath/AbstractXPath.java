package nl.agentsatwork.xpath;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.Tree;

import nl.agentsatwork.antlr.TLexer;
import nl.agentsatwork.antlr.TParser;
import nl.agentsatwork.antlr.TParser.a_return;

public class AbstractXPath {
	final static private TLexer lexer = new TLexer();

	protected Tree parse(String xpath) throws RecognitionException {
		lexer.setCharStream(new ANTLRStringStream());
		TParser parser = new TParser(new CommonTokenStream(lexer));
		a_return psrReturn = parser.a();
		return (Tree) psrReturn.getTree();
	}

}
