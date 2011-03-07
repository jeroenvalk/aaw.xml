package nl.agentsatwork.attributes;

import java.util.Map;

public interface Attributes extends ImmutableAttributes, MutableAttributes {

	public Map<String, String> attr();

}
