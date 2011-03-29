package nl.agentsatwork.elements;

import org.antlr.runtime.tree.Tree;

import nl.agentsatwork.collection.Tuple;
import nl.agentsatwork.element.Element;

public interface Elements extends Tuple<Element>{

	String getTagName();
	
	Tuple<String> tagnames();

	Tuple<Element> xpath(Tree path);
	
}
