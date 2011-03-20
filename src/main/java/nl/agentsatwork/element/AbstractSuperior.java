package nl.agentsatwork.element;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import nl.agentsatwork.attribute.Attribute;
import nl.agentsatwork.attributes.Attributes;
import nl.agentsatwork.attributes.ImmutableAttributes;
import nl.agentsatwork.collection.AbstractImmutableSet;

public class AbstractSuperior implements Superior {

	final protected Attributes attr;
	private AbstractSuperior superior = null;
	private Set<String> tagnames = new HashSet<String>();

	public AbstractSuperior(Attribute[] attribute) {
		this.attr = newAttr(attribute);
	}

	protected Attributes newAttr(Attribute[] attribute) {
		return null;
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

	public Set<String> tagnames() {
		return new AbstractImmutableSet<String>() {

			public int size() {
				return tagnames.size();
			}

			public boolean contains(Object o) {
				return tagnames.contains(o);
			}

			public Iterator<String> iterator() {
				final Iterator<String> it = tagnames.iterator();
				return new Iterator<String>() {

					public boolean hasNext() {
						return it.hasNext();
					}

					public String next() {
						return it.next();
					}

					public void remove() {
						throw new UnsupportedOperationException();
					}
					
				};
			}
			
		};
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
