package nl.agentsatwork.attribute;

import java.util.ArrayList;
import java.util.List;

final public class DefaultEntityImpl implements DefaultEntity {

	final private List<String> keys = new ArrayList<String>();
	final private List<AbstractAttribute> attributes = new ArrayList<AbstractAttribute>();

	final public void register(String key, AbstractAttribute attribute) {
		assert keys.size() == attributes.size();
		keys.add(key);
		attributes.add(attribute);
	}

	final public int index(AbstractAttribute attribute) {
		return attributes.indexOf(attribute);
	}

	final public int index(String key) {
		throw new UnsupportedOperationException();
	}

	final public String name(int index) {
		return keys.get(index);
	}

	final public AbstractAttribute attribute(int index) {
		return attributes.get(index);
	}

	final public boolean register(AbstractAttribute attribute) {
		nl.agentsatwork.attributes.Entity superior = attribute.getSuperior();
		if (superior == null) {
			if (attributes.indexOf(attribute) < 0) {
				superior = AbstractAttribute.defaultEntity;
				int index = superior.index(attribute);
				if (index < 0) {
					return false;
				} else {
					String name = superior.name(index);
					register(name,attribute);
					attribute.setEntity(this);
					return true;
				}
			} else {
				return true;
			}
		} else if (superior == this) {
			if (attributes.indexOf(attribute) < 0) {
				throw new IllegalStateException();
			} else {
				return true;
			}
		} else {
			if (attributes.indexOf(attribute) < 0) {
				String name = superior.name(superior.index(attribute));
				if (superior.unregister(attribute)) {
					register(name, attribute);
					attribute.setEntity(this);
					return true;
				} else {
					return false;
				}
			} else {
				if (superior.unregister(attribute)) {
					return true;
				} else {
					throw new IllegalStateException();
				}
			}
		}
	}

	final public boolean unregister(AbstractAttribute attribute) {
		int index = attributes.indexOf(attribute);
		if (index >= 0) {
			attributes.set(index, null);
			attribute.setEntity(null);
		}
		return true;
	}

}
