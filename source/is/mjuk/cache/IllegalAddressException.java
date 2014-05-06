package is.mjuk.cache;

/**
* IllegalAddressException is a throwable exception for 
*/
public class IllegalAddressException extends Exception {
	public IllegalAddressException() {
		super();
	}


	public IllegalAddressException(String errormsg) {
		super(errormsg);
	}

	public IllegalAddressException(Throwable throwable) {
		super(throwable);
	}

	public IllegalAddressException(String errormsg, Throwable throwable) {
		super(errormsg, throwable);
	}
}