package nl.agentsatwork.aggregates;


public interface AggregateIndex extends Index {

	int autonumerical(Object value);

	void remove(int i);

}
