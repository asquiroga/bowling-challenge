package com.jobsity.challenge.parser.exception;

/**
 * This class represents an exception during the parsing 
 * 
 * @author alfonsosebaq
 */
public class BowlingParserException extends Exception {

	private static final long serialVersionUID = 1L;

	public BowlingParserException(String message) {
		super(message);
	}

	public BowlingParserException(String message, Exception exception) {
		super(message, exception);
	}

}
