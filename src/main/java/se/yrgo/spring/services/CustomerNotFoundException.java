package se.yrgo.spring.services;

/**
 * Exception thrown by the service layer when no matching customer
 * can be found for the provided identifier(s).
 * This exception is typically used to indicate that a search query for a 
 * customer (by ID or name) returned no results.
 */
public class CustomerNotFoundException extends Exception {

    public CustomerNotFoundException(String message) {
        super(message);
    }
    // this is just to stop warnings
	private static final long serialVersionUID = 1L;
}
