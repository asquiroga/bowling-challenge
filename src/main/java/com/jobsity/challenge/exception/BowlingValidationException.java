package com.jobsity.challenge.exception;

/**
 * This class represents an exception during the parsing 
 * 
 * @author alfonsosebaq
 */
public class BowlingValidationException extends Exception {

	private static final long serialVersionUID = 1L;

	public BowlingValidationException(String message) {
		super(message);
	}

	public BowlingValidationException(String message, Exception exception) {
		super(message, exception);
	}

}
