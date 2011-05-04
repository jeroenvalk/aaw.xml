package nl.agentsatwork.aggregates;

import org.junit.Before;
import org.junit.Test;

public class AbstractStructureTest {
	private class Property {
		public String name = null;
		public String value = null;

		public Property(String name, String value) {
			this.name = name;
			this.value = value;
		}
	}

	private class Properties {
		public Property[] properties = new Property[] {
				new Property("hello", "world"), new Property("bye", "world") };
	}

	private class Example {
		private Properties properties = new Properties();

		public Properties getProperties() {
			return properties;
		}
	}

	private Example example = null;

	@Before
	public void setUp() {
		example = new Example();
	}

	@Test
	public void test() {
		final Structure root = new RootStructure(new Example[] { example });

		final Structure properties = new AbstractStructure<Example>() {
			public Structure getParent() {
				return root;
			}


			@Override
			protected Object getValue(Example value, int i) {
				switch (i) {
				case 0:
					return value.getProperties();
				default:
					return null;
				}
			}

			@Override
			protected int limit(Example value) {
				return 1;
			}

		};

		Structure property = new AbstractStructure<Properties>() {

			public Structure getParent() {
				return properties;
			}

			@Override
			protected Object getValue(Properties value, int i) {
				return value.properties[i];
			}

			@Override
			protected int limit(Properties value) {
				return value.properties.length;
			}

		};
	}

}
