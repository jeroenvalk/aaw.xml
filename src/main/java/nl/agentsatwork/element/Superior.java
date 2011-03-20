package nl.agentsatwork.element;

public interface Superior extends nl.agentsatwork.attributes.Superior {

	boolean hasElement(Element element);
	
	boolean register(Element elements);
	
	boolean unregister(Element elements);
	
}
