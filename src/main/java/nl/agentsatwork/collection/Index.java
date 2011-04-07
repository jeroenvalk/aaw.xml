package nl.agentsatwork.collection;

public interface Index<A> {

	int autonumerical();
	
	int offset();
	
	int limit();
	
	int indexOf(A value);
	
	A valueOf(int index);
	
	void valueOf(int index, A value);
	
	int size();
	
}
