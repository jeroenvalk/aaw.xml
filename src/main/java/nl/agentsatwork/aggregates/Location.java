package nl.agentsatwork.aggregates;

public interface Location {

	Aggregate getAggregate();

	void setAggregate(Aggregate aggregate);
	
	int getPosition();
	
	void setPosition(int position);
	
}
