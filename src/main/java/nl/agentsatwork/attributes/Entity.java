package nl.agentsatwork.attributes;

import nl.agentsatwork.attribute.AbstractAttribute;

public interface Entity {

	void register(String key, AbstractAttribute attribute);

	int index(AbstractAttribute attribute);

	int index(String name);
	
	String name(int index);
	
	AbstractAttribute attribute(int index);
	
	boolean register(AbstractAttribute abstractAttribute);
	
	boolean unregister(AbstractAttribute attribute);

}
