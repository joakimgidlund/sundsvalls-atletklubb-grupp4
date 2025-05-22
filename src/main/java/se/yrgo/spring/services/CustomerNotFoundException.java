package se.yrgo.spring.services;

public class CustomerNotFoundException extends Exception {

    public CustomerNotFoundException(String message) {
        super(message);
    }
    // this is just to stop warning
	private static final long serialVersionUID = 1L;
}
