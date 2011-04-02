package nl.agentsatwork.attributes;

import nl.agentsatwork.attribute.AbstractAttribute;
import nl.agentsatwork.elements.Superior;
import nl.agentsatwork.xpath.AbstractXPath;

public class AbstractEntity extends AbstractXPath implements Entity {

	private Superior aggregate = null;
	private AbstractAttribute[] attr = null;
	
	final public int index(AbstractAttribute attribute) {
		for (int i =0; i<attr.length; ++i) {
			if (attr[i] == attribute) {
				return i;
			}
		}
		return -1;
	}

	final public int index(String name) {
		return aggregate.index(name);
	}

	final public String name(int index) {
		return aggregate.name(index);
	}

	final public AbstractAttribute attribute(int index) {
		return attr[index];
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
		int index = index(attribute);
		if (index >= 0) {
			attr[index] = null;
			attribute.setEntity(null);
		}
		return false;
	}

	public void register(String key, AbstractAttribute attribute) {
		// TODO Auto-generated method stub
		
	}

}
