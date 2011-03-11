package nl.agentsatwork.xml;

import java.util.Iterator;

import nl.agentsatwork.elements.Elements;

abstract public class XML<A,B> implements Tag, Elements {
	protected Iterator<B> _ = null;
	
	abstract public void refresh(A y);
	
	protected Iterator<B> singleton(final B x) {
		return new Iterator<B>() {
			private B current = x;

			public boolean hasNext() {
				return current != null;
			}

			public B next() {
				B result = current;
				current = null;
				return result;
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}
			
		};
	}
	
	protected Iterator<B> array(final B[] x) {
		return new Iterator<B>() {
			private int i = 0;

			public boolean hasNext() {
				return i < x.length;
			}

			public B next() {
				return x[i];
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}
			
		};
	}
}
