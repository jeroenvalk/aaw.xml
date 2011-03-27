package nl.agentsatwork.elements;

import nl.agentsatwork.collection.Tuple;
import nl.agentsatwork.element.Element;

public interface Elements extends Tuple<Element>{

	String getTagName();
	
	Tuple<String> tagnames();
	
}
