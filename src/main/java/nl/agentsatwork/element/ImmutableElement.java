package nl.agentsatwork.element;

import java.util.Map;

import nl.agentsatwork.attribute.Attribute;
import nl.agentsatwork.attributes.ImmutableAttributes;
import nl.agentsatwork.attributes.Attributes;
import nl.agentsatwork.elements.Elements;

public class ImmutableElement extends AbstractSuperior implements Element {

	final private String tagname;
	
	public ImmutableElement(String tagname, Attribute[] attribute, Elements[] elements) {
		super(attribute);
		this.tagname = tagname;
		if (elements != null) {
			for (int i=0; i<elements.length; ++i) {
				this.r
			}
		}
	}

	protected Attributes newAttr(Attribute[] attribute) {
		final Superior self = (Superior) this;
		return new ImmutableAttributes(attribute) {
			public String valueOf(String name) {
				return self.valueOf(name);
			}			
		};		
	}
	
	public String getTagName() {
		return tagname;
	}

	public boolean hasAttribute(String name) {
		return attr.hasAttribute(name);
	}

	public String get(String name) {
		return attr.get(name);
	}

	public void set(String name, String value) {
		attr.set(name, value);
	}

	public Map<String, String> attr() {
		return attr.attr();
	}

	@Override
	public String valueOf(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterable<Element> ancestors() {
		// TODO Auto-generated method stub
		return null;
	}

	public Elements xpath(Element path) {
		// TODO Auto-generated method stub
		return null;
	}

}
