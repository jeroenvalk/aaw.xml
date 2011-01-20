package nl.aaw.bootstrap;

public interface Initializable<A> {
	
	A init(String xpath);
	
	boolean isInitializable();
	
	boolean isInitialized();
	
}
