package nl.agentsatwork.attribute;

import java.util.Map.Entry;

public interface Attribute extends Entry<String, String> {

	public String defaultValue();
	
}
