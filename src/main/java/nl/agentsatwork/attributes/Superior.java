package nl.agentsatwork.attributes;

import java.util.Set;

import nl.agentsatwork.attribute.Attribute;
import nl.agentsatwork.elements.Elements;

public interface Superior {

	Set<String> attributes();
	
	Attribute attribute(String key);
	
	boolean register(Attribute attribute);
	
	boolean unregister(Attribute attribute);

	String valueOf(String name);
	
}
