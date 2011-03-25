package nl.agentsatwork.element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nl.agentsatwork.attribute.Attribute;
import nl.agentsatwork.attributes.ImmutableAttributes;
import nl.agentsatwork.attributes.Attributes;
import nl.agentsatwork.collection.AbstractTuple;
import nl.agentsatwork.collection.Tuple;
import nl.agentsatwork.elements.AbstractSuperior;
import nl.agentsatwork.elements.Elements;
import nl.agentsatwork.elements.Superior;

public class ImmutableElement extends AbstractSuperior implements Element {

	public ImmutableElement(Elements siblings, Attribute[] attribute, Elements[] elements) {
		super(attribute, elements);
	}

	protected Attributes newAttr(Attribute[] attribute) {
		final Superior self = (Superior) this;
		return new ImmutableAttributes(attribute) {
			public String valueOf(String name) {
				return self.valueOf(name);
			}
		};
	}

	public boolean hasTagName(String tagname) {
		return super.siblings().getTagName().equals(tagname);
	}

	public String getTagName() {
		return super.siblings().getTagName();
	}

	public boolean hasAttribute(String name) {
		return super.attribute(name) != null;
	}

	public String get(String name) {
		return super.attribute(name).getValue();
	}

	public void set(String name, String value) {
		throw new UnsupportedOperationException();
	}

	public Map<String, String> attr() {
		return null;
	}

	public Tuple<Element> xpath(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	public Tuple<Element> xpath(Element path) {
		if (!path.hasTagName("xpath")) {
			throw new IllegalArgumentException("protocol error");
		}
		Iterator<Element> it = path.getElementsByTagName("xstep").iterator();
		if (!it.hasNext()) {
			throw new IllegalArgumentException("protocol error");
		}
		Element step = it.next();
		if (step.hasAttribute("axis")) {
			if (step.get("axis").equals("self")) {
				for (Element predicate : step.getElementsByTagName("predicate")) {
					if (!this.evaluate(predicate)) {
						return null;
					}
				}
				if (it.hasNext()) {
					it.remove();
				} else {
					final Element result = this;
					return new AbstractTuple<Element>() {

						public Element get(int index) {
							if (index == 0) {
								return result;
							} else {
								return null;
							}
						}

						public int size() {
							return 1;
						}

					};
				}
			} else if (step.get("axis").equals("parent")) {
				step.set("axis", "self");
				return this.parent().xpath(path);
			} else {
				it = path.getElementsByTagName("xstep").iterator();
			}
		}
		List<Element> steps = new ArrayList<Element>();
		while (it.hasNext()) {
			step = it.next();
			String axis, tagname;
			if (step.hasAttribute("axis")) {
				axis = step.get("axis");
			} else {
				axis = "child";
			}
			if (step.hasAttribute("name")) {
				tagname = step.get("name");
			} else {
				tagname = null;
			}
			if (axis.equals("child")) {
				if (tagname == null) {
					throw new IllegalArgumentException("not implemented");
				} else {
					steps.add(step);
					it.remove();
				}
			} else if (axis.equals("parent")) {
				steps.remove(steps.size() - 1);
				it.remove();
				if (!step.getElementsByTagName("predicate").isEmpty()) {
					throw new IllegalArgumentException("not implemented");
				}
			} else if (axis.equals("self")) {
				it.remove();
				if (!step.getElementsByTagName("predicate").isEmpty()) {
					throw new IllegalArgumentException("not implemented");
				}
			} else {
				break;
			}
		}
		String[] tagnames = new String[steps.size()];
		for (int i = 0; i < tagnames.length; ++i) {
			assert steps.get(i).hasAttribute("name");
			tagnames[i] = steps.get(i).get("name");
		}
		return siblings().getElementsByTagPath(tagnames).reverse(steps);
	}

	private boolean evaluate(Element predicate) {
		return false;
	}

}
