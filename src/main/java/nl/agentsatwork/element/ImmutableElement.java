package nl.agentsatwork.element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.Tree;

import nl.agentsatwork.antlr.XPath;
import nl.agentsatwork.attribute.Attribute;
import nl.agentsatwork.attributes.AbstractSuperior;
import nl.agentsatwork.attributes.ImmutableAttributes;
import nl.agentsatwork.attributes.Attributes;
import nl.agentsatwork.collection.AbstractTuple;
import nl.agentsatwork.collection.Tuple;
import nl.agentsatwork.elements.Elements;
import nl.agentsatwork.elements.Superior;

public class ImmutableElement extends ImmutableAttributes implements Element {

	private Superior superior = null;
	final Map<String, Elements> elements = new HashMap<String, Elements>();

	public ImmutableElement(Attribute[] attribute, Elements[] elements) {
		super(attribute);
	}

	public boolean hasTagName(String tagname) {
		return getTagName().equals(tagname);
	}

	public String getTagName() {
		return superior.siblings().getTagName();
	}

	public Tuple<Element> xpath(String path) throws RecognitionException {
		Tree tree = parse(path);
		switch (tree.getType()) {
		case 0:
			return xpath(tree);
		case XPath.PATHSEP:
		case XPath.ABRPATH:
			assert false;
			break;
		default:
			assert false;
			break;
		}
		return null;
	}

	public Tuple<Element> xpath(Tree path) {
		switch (path.getType()) {
		case XPath.PATHSEP:
			return superior.siblings().xpath(path);
		case XPath.PATH:
			assert path.getChildCount() > 0;
			switch (path.getChild(0).getType()) {
			case XPath.Following:
			case XPath.FollowingSibling:
			case XPath.Namespace:
			case XPath.Preceding:
			case XPath.PrecedingSibling:
				assert false;
				return null;
			case XPath.Ancestor:
			case XPath.Parent:
				return superior.siblings().xpath(path);
			case XPath.Child:
			case XPath.Descendant:
			case XPath.Attribute:

			case XPath.AncestorOrSelf:
			case XPath.DescendantOrSelf:
			case XPath.Self:
			default:
				assert false;
				return null;

			}
		default:
			assert false;
			return null;

		}
	}
	/*
	 * if (!path.hasTagName("xpath")) { throw new
	 * IllegalArgumentException("protocol error"); } Iterator<Element> it =
	 * path.getElementsByTagName("xstep").iterator(); if (!it.hasNext()) { throw
	 * new IllegalArgumentException("protocol error"); } Element step =
	 * it.next(); if (step.hasAttribute("axis")) { if
	 * (step.get("axis").equals("self")) { for (Element predicate :
	 * step.getElementsByTagName("predicate")) { if (!this.evaluate(predicate))
	 * { return null; } } if (it.hasNext()) { it.remove(); } else { final
	 * Element result = this; return new AbstractTuple<Element>() {
	 * 
	 * public Element get(int index) { if (index == 0) { return result; } else {
	 * return null; } }
	 * 
	 * public int size() { return 1; }
	 * 
	 * }; } } else if (step.get("axis").equals("parent")) { step.set("axis",
	 * "self"); return this.parent().xpath(path); } else { it =
	 * path.getElementsByTagName("xstep").iterator(); } } List<Element> steps =
	 * new ArrayList<Element>(); while (it.hasNext()) { step = it.next(); String
	 * axis, tagname; if (step.hasAttribute("axis")) { axis = step.get("axis");
	 * } else { axis = "child"; } if (step.hasAttribute("name")) { tagname =
	 * step.get("name"); } else { tagname = null; } if (axis.equals("child")) {
	 * if (tagname == null) { throw new
	 * IllegalArgumentException("not implemented"); } else { steps.add(step);
	 * it.remove(); } } else if (axis.equals("parent")) {
	 * steps.remove(steps.size() - 1); it.remove(); if
	 * (!step.getElementsByTagName("predicate").isEmpty()) { throw new
	 * IllegalArgumentException("not implemented"); } } else if
	 * (axis.equals("self")) { it.remove(); if
	 * (!step.getElementsByTagName("predicate").isEmpty()) { throw new
	 * IllegalArgumentException("not implemented"); } } else { break; } }
	 * String[] tagnames = new String[steps.size()]; for (int i = 0; i <
	 * tagnames.length; ++i) { assert steps.get(i).hasAttribute("name");
	 * tagnames[i] = steps.get(i).get("name"); } return
	 * siblings().getElementsByTagPath(tagnames).reverse(steps); }
	 * 
	 * private boolean evaluate(Element predicate) { return false; }
	 */
}
