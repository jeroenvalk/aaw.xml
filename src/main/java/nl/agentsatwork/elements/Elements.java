package nl.agentsatwork.elements;

import java.util.List;

import nl.agentsatwork.collection.Tuple;
import nl.agentsatwork.element.Element;

public interface Elements extends Tuple<Element> {

	Elements getElementsByTagPath(String[] tagnames);

	Tuple<Element> reverse(List<Element> steps);
	
}
