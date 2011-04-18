package nl.agentsatwork.aggregates;

public class IndependentLocation implements Location {

	private Aggregate aggregate = null;
	private int position = -1;
	
	public Aggregate getAggregate() {
		return aggregate;
	}

	public void setAggregate(Aggregate aggregate) {
		this.aggregate = aggregate;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

}
