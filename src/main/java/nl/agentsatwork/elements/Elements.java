package nl.agentsatwork.elements;

import nl.agentsatwork.aggregates.Index;
import nl.agentsatwork.collection.Tuple;
import nl.agentsatwork.element.Element;

import org.antlr.runtime.tree.Tree;

public interface Elements extends Tuple<Element> {

	String getTagName();

	Index getTagNames();
	
	Index getAttributeNames();

	Tuple<Element> xpath(Tree path);
	
}
