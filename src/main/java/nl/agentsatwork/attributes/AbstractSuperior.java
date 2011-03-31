package nl.agentsatwork.attributes;

import java.util.HashMap;
import java.util.Map;

import nl.agentsatwork.attribute.Attribute;
import nl.agentsatwork.xpath.AbstractXPath;

abstract public class AbstractSuperior extends AbstractXPath implements Superior {

	final private Map<String, Attribute> attr = new HashMap<String, Attribute>();
	
	final public Attribute attribute(String key) {
		return attr.get(key);
	}

	final public boolean register(Attribute attribute) {
		String key = attribute.getKey();
		Attribute org = attr.put(key, attribute);
		if (org == null) {
			return true;
		} else {
			assert key.equals(org.getKey());
			attr.put(key, org);
			return false;
		}
	}

	final public boolean unregister(Attribute attribute) {
		String key = attribute.getKey();
		Attribute org = attr.remove(key);
		if (org == attribute) {
			return true;
		} else {
			if (org == null) {
				return false;
			} else {
				assert key.equals(org.getKey());
				attr.put(key, org);
				return false;
			}
		}
	}

}
