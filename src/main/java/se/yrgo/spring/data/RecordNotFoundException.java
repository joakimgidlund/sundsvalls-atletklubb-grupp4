package se.yrgo.spring.data;

public class RecordNotFoundException extends Exception {

	public RecordNotFoundException(String message) {
        super(message);
    }

    /**
	 * Just to stop warnings.
	 */
	private static final long serialVersionUID = 1L;

}