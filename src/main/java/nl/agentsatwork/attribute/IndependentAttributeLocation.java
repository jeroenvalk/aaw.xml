package nl.agentsatwork.attribute;

import nl.agentsatwork.aggregates.IndependentLocation;

public class IndependentAttributeLocation extends IndependentLocation implements AttributeLocation {

	final private String key;
	
	public IndependentAttributeLocation(String key) {
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}

}
