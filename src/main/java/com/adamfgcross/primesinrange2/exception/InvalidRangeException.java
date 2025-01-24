package com.adamfgcross.primesinrange2.exception;

public class InvalidRangeException extends RuntimeException {
	private static final long serialVersionUID = 459165836996314018L;

	// No-argument constructor
    public InvalidRangeException() {
        super();
    }

    // Constructor with a custom message
    public InvalidRangeException(String message) {
        super(message);
    }

    // Constructor with a custom message and cause
    public InvalidRangeException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor with only a cause
    public InvalidRangeException(Throwable cause) {
        super(cause);
    }
}
