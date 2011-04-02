package nl.agentsatwork.attributes;

import java.util.HashMap;
import java.util.Map;

import nl.agentsatwork.attribute.AbstractAttribute;
import nl.agentsatwork.attribute.Attribute;
import nl.agentsatwork.xpath.AbstractXPath;

public class AbstractEntity extends AbstractXPath implements Entity {

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

	public int index(AbstractAttribute attribute) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int index(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String name(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public AbstractAttribute attribute(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean register(AbstractAttribute abstractAttribute) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean unregister(AbstractAttribute attribute) {
		// TODO Auto-generated method stub
		return false;
	}

}
