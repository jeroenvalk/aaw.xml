package nl.agentsatwork.element;

import java.util.Map;

import nl.agentsatwork.attributes.AbstractSuperior;
import nl.agentsatwork.attributes.Attributes;
import nl.agentsatwork.elements.Elements;
import nl.agentsatwork.elements.Superior;

public class ImmutableElement extends AbstractSuperior implements Element {

	final private String tagname;
	final private Attributes attr;
	final private Superior descendants;
	
	public ImmutableElement(Superior superior, String tagname, Attributes attr, Elements[] descendant) {
		this.superior = superior;
		this.tagname = tagname;
		this.attr = attr;
		if (descendant == null) {
			descendants = null;
		} else {
			
		}
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

	public Elements xpath(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String valueOf(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
