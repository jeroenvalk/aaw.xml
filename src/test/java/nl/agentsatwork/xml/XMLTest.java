package nl.agentsatwork.xml;

import nl.agentsatwork.elements.Elements;


public class XMLTest {
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

	public void setUp() {
		example = new Example();
	}

	public void testXML() {
		Tag example = new XML<Example,Example>() {

			@Override
			public void refresh(Example y) {
				_ = this.singleton(y);
			}

			Tag properties = new XML<Example,Properties>() {

				@Override
				public void refresh(Example y) {
					_ = this.singleton(y.getProperties());
				}
			
				Tag property = new XML<Properties,Property>() {

					@Override
					public void refresh(Properties y) {
						_ = this.array(y.properties);
					}
					
				};
			};
			
		};
	}

}
