package nl.agentsatwork.elements;

import java.util.List;

import nl.agentsatwork.element.Element;

public interface Superior extends nl.agentsatwork.attributes.Superior {

	List<String> tagnames();
	
	List<Element> elements(String tagname);
	
	boolean register(Element element);
	
	boolean unregister(Element element);
	
}
