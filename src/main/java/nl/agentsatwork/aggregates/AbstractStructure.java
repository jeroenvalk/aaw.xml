package nl.agentsatwork.aggregates;

abstract public class AbstractStructure<A> extends AbstractComposite<Object>
		implements Structure {

	final private AggregateIndex index;
	
	public AbstractStructure(Structure parent) {
		index = new AbstractAggregateIndex() {

			public Location getLocation() {
				return new IndependentLocation();
			}

			public int indexOf(Object value) {
				throw new UnsupportedOperationException();
			}
			
		};
		parent.getAggregateIndex().enter(this);
	}
	
	public AggregateIndex getAggregateIndex() {
		return index;
	}
	
	private Structure getParent() {
		return (Structure) getAggregateIndex().getLocation().getAggregate();
	}
	
	public void refresh() {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	public void refresh(int i) {
		Index index = getIndex(i);
		A value = (A) getParent().getValue(i);
		int j = 0, m = index.limit(), n = limit(value);
		for (i = index.offset(); i < m; ++i) {
			if (n <= j)
				break;
			if (index.valueOf(i) == getValue(value, j)) {
				++j;
			} else {
				if (!index.leave(i)) {
					assert false;
				}
			}
		}
		for (j = i; j < m; ++j) {
			if (!index.leave(j)) {
				assert false;
			}
		}
	}

	abstract protected Object getValue(A value, int i);

	abstract protected int limit(A value);

}
