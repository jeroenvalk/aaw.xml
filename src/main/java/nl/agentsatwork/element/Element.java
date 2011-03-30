package nl.agentsatwork.element;

import nl.agentsatwork.attributes.Attributes;
import nl.agentsatwork.collection.Tuple;

import org.antlr.runtime.RecognitionException;

public interface Element extends Attributes {

	boolean hasTagName(String tagname);
	
	String getTagName();

	Tuple<Element> xpath(String path) throws RecognitionException;
	
}
