package nl.agentsatwork.element;

import nl.agentsatwork.attributes.Attributes;
import nl.agentsatwork.collection.Tuple;
import nl.agentsatwork.elements.Elements;

public interface Element extends Attributes {

	boolean hasTagName(String tagname);
	
	String getTagName();

	Tuple<Element> xpath(String path);
	
}
