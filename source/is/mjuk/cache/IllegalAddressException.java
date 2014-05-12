package is.mjuk.cache;

/**
* IllegalAddressException is a throwable exception for addresses that's not
* legally readable.
* <p>
* For example, addresses that's not evenly divisible by the word size,
* are out of bounds or simply doesn't make sense. 
*/
public class IllegalAddressException extends Exception {
	/**
	* Constructor for an IllegalAddressException called without any error 
	* errormessage or a cause-throwable attached to it.
	*/
	public IllegalAddressException() {
		super();
	}

	/**
	* Constructor containing a message for specifying details.
	* @param errormsg Details of the exception.
	*/
	public IllegalAddressException(String errormsg) {
		super(errormsg);
	}
}