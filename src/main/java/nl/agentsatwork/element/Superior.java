package nl.agentsatwork.element;

import java.util.Set;

public interface Superior extends nl.agentsatwork.attributes.Superior {

	Set<String> tagnames();
	
	boolean hasElement(Element element);
	
	boolean register(Element elements);
	
	boolean unregister(Element elements);
	
}
