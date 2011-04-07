package nl.agentsatwork.attributes;

import java.util.AbstractMap;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;

import nl.agentsatwork.attribute.Attribute;

public class MutableAttributes extends AbstractEntity {

	public MutableAttributes(Attribute[] attribute) {

	}

	public void set(String name, String value) {
		Set<Entry<String,String>> entrySet = entrySet();
		if (entrySet)
	}

	public Map<String, String> attr() {
		return new AbstractMap<String,String>() {

			@Override
			public Set<java.util.Map.Entry<String, String>> entrySet() {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
	}
}
