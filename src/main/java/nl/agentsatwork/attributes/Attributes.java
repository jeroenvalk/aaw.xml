package nl.agentsatwork.attributes;

import java.util.Map;

public interface Attributes {

	public boolean hasAttribute(String name);

	public String get(String name);

	public void set(String name, String value);

	public Map<String, String> attr();

}
