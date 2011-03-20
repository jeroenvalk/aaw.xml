package nl.agentsatwork.element;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import nl.agentsatwork.attribute.Attribute;
import nl.agentsatwork.attributes.Attributes;
import nl.agentsatwork.attributes.ImmutableAttributes;
import nl.agentsatwork.collection.AbstractTuple;
import nl.agentsatwork.collection.Tuple;

public class AbstractSuperior implements Superior {

	final protected Attributes attr;
	private AbstractSuperior superior = null;

	public AbstractSuperior(Attribute[] attribute) {
		this.attr = newAttr(attribute);
	}

	protected Attributes newAttr(Attribute[] attribute) {
		return null;
	}

	protected Element parent() {
		return (Element) superior;
	}
	
	protected Tuple<Element> ancestors() {
		final List<Element> result = new ArrayList<Element>();
		AbstractSuperior current = this;
		while (current != null) {
			result.add((Element) current);
			current = current.superior;
		}
		return new AbstractTuple<Element>() {

			@Override
			public Element get(int index) {
				return result.get(index);
			}

			@Override
			public int size() {
				return result.size();
			}
			
		};
	}
	
	public Set<String> attributes() {
		return ((ImmutableAttributes) attr).attributes();
	}

	public Attribute attribute(String key) {
		return ((ImmutableAttributes) attr).attribute(key);
	}

	public boolean register(Attribute attribute) {
		return ((ImmutableAttributes) attr).register(attribute);
	}

	public boolean unregister(Attribute attribute) {
		return ((ImmutableAttributes) attr).unregister(attribute);
	}

	public String valueOf(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasElement(Element element) {
		if (element instanceof AbstractSuperior) {
			return ((AbstractSuperior) element).superior == this;
		} else {
			return false;
		}
	}

	public boolean register(Element element) {
		if (element instanceof AbstractSuperior) {
			if (element == this) {
				return false;
			} else if (((AbstractSuperior) element).superior == null) {
				((AbstractSuperior) element).superior = this;
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean unregister(Element element) {
		if (element instanceof AbstractSuperior) {
			if (((AbstractSuperior) element).superior == this) {
				((AbstractSuperior) element).superior = null;
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}