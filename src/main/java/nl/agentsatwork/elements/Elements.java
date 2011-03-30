package nl.agentsatwork.elements;

import nl.agentsatwork.collection.Tuple;
import nl.agentsatwork.element.Element;

import org.antlr.runtime.tree.Tree;

public interface Elements extends Tuple<Element>{

	String getTagName();
	
	Tuple<String> tagnames();

	Tuple<Element> xpath(Tree path);
	
}
