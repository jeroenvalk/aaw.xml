package nl.agentsatwork.aggregates;

public interface Structure extends Composite<Object> {

	Structure getParent();

	void refresh();

	void refresh(int i);

}
