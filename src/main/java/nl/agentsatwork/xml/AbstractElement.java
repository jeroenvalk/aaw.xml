package nl.agentsatwork.xml;

import java.util.Map;

import nl.agentsatwork.attributes.Attributes;

public class AbstractElement extends ProxyAttributes implements Attributes {

	private Attributes cache = null;

	public String get(String name) {
		assert attrMap != null;
		if (attrMap.containsKey(name)) {
			if (cache == null) {
				return super.get(name);
			} else {
				if (cache.hasAttribute(name)) {
					return cache.get(name);
				} else {
					if (super.hasAttribute(name)) {
						String result = super.get(name);
						cache.set(name, result);
						return result;
					} else {
						return null;
					}
				}
			}
		} else {
			return null;
		}
	}

	public void set(String name, String value) {
		if (cache == null) {
			super.set(name, value);
		} else {
			cache.set(name, value);
		}
	}

	public Map<String, String> attr() {
		// TODO Auto-generated method stub
		return null;
	}
}
