package nl.agentsatwork.attributes;

import java.util.Map.Entry;
import java.util.Set;

import nl.agentsatwork.attribute.AbstractAttribute;

public interface Entity {

	void register(String key, AbstractAttribute attribute);

	int index(AbstractAttribute attribute);

	int index(String name);
	
	String name(int index);
	
	AbstractAttribute attribute(int index);
	
	boolean register(AbstractAttribute abstractAttribute);
	
	boolean unregister(AbstractAttribute attribute);
	
	Set<Entry<String, String>> entrySet();

}
