package nl.agentsatwork.aggregates;

public interface Composite<A> {

	int i(int k);

	int j(int k);

	int k(int i, int j);

	Index getIndex(int i);

	A getValue(int k);

	void resize(int size);

	int size();

}
