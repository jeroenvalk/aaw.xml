package nl.agentsatwork.elements;

import nl.agentsatwork.element.Element;

public interface Superior extends nl.agentsatwork.attributes.Superior {

	Elements siblings();

	Elements elements(String tagname);
	
	boolean register(Element elements);
	
	boolean unregister(Element elements);
	
}
