package nl.agentsatwork.element;

import nl.agentsatwork.attributes.Attributes;
import nl.agentsatwork.collection.Tuple;
import nl.agentsatwork.elements.Elements;

public interface Element extends Attributes, Iterable<String> {

	Elements siblings();

	boolean hasTagName(String tagname);
	
	Elements getElementsByTagName(String tagname);
	
	Tuple<Element> xpath(String path);
	
	Tuple<Element> xpath(Element path);
	
}
