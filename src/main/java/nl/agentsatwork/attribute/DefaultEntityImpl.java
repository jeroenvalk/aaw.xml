package nl.agentsatwork.attribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import nl.agentsatwork.attributes.Entity;

final public class DefaultEntityImpl implements Entity {

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
		Entity entity = attribute.getSuperior();
		if (entity == null) {
			if (index(attribute) < 0) {
				entity = AbstractAttribute.defaultEntity;
				int index = entity.index(attribute);
				if (index < 0) {
					return false;
				} else {
					String name = entity.name(index);
					register(name,attribute);
					attribute.setEntity(this);
					return true;
				}
			} else {
				return true;
			}
		} else if (entity == this) {
			if (index(attribute) < 0) {
				throw new IllegalStateException();
			} else {
				return true;
			}
		} else {
			if (index(attribute) < 0) {
				String name = entity.name(entity.index(attribute));
				if (entity.unregister(attribute)) {
					register(name, attribute);
					attribute.setEntity(this);
					return true;
				} else {
					return false;
				}
			} else {
				if (entity.unregister(attribute)) {
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

	public Set<Entry<String, String>> entrySet() {
		throw new UnsupportedOperationException();
	}

}
