package nl.agentsatwork.aggregates;

public interface Index {

	int enter(Object value);

	int offset();

	int limit();

	int indexOf(Object value);

	Object valueOf(int index);

	boolean leave(int index);

	int size();

}
