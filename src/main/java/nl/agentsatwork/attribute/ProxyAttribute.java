package nl.agentsatwork.attribute;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import nl.agentsatwork.attributes.Superior;

final public class ProxyAttribute extends AbstractAttribute {

	Method[] getValue = null;

	public ProxyAttribute(Superior superior, String key) {
		super(superior, key);
	}

	final public String getValue() {
		if (value == null) {
			for (int i = 0; i < getValue.length; ++i) {
				try {
					return (String) getValue[i].invoke(this);
				} catch (InvocationTargetException e) {

				} catch (IllegalArgumentException e) {

				} catch (IllegalAccessException e) {

				}
			}
			return null;
		} else {
			return value;
		}
	}

}
