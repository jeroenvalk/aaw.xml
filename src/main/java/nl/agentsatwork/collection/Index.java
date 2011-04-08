package nl.agentsatwork.collection;

public interface Index<A> {

	void autonumerical(A value);
	
	int offset();
	
	int limit();
	
	int indexOf(A value);
	
	A valueOf(int index);
	
	void remove(int index);
	
	int size();
	
}
