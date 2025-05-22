package se.yrgo.spring.services;

public class GymClassNotFoundException extends Exception {

    public GymClassNotFoundException(String message) {
        super(message);
    }

    /**
     * Just to stop warnings.
     */
    private static final long serialVersionUID = 2L;
}
