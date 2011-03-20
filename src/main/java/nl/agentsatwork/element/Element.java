package nl.agentsatwork.element;

import nl.agentsatwork.attributes.Attributes;
import nl.agentsatwork.elements.Elements;

public interface Element extends Attributes {

	Iterable<Element> ancestors();
	
	Elements xpath(Element path);
	
}
