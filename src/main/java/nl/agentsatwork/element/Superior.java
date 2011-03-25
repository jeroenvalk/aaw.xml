package nl.agentsatwork.element;

import nl.agentsatwork.elements.Elements;

public interface Superior extends nl.agentsatwork.attributes.Superior {

	Elements siblings();

	Elements elements(String tagname);
	
	boolean register(Element elements);
	
	boolean unregister(Element elements);
	
}
