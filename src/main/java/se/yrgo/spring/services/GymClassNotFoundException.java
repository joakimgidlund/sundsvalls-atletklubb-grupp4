package se.yrgo.spring.services;

/**
 * Custom exception thrown from the service layer when an
 * object cannot be found.
 * 
 * @author joakimgidlund
 */
public class GymClassNotFoundException extends Exception {

    public GymClassNotFoundException(String message) {
        super(message);
    }

    /**
     * Just to stop warnings.
     */
    private static final long serialVersionUID = 2L;
}
