package com.ftpserver.exceptions;

/**
 * Pass exception is the exception returned when TODO insert cases of error here]
 * @author Aurélien Plancke
 *
 */
public class PassException extends CommandException {

	private static final long serialVersionUID = -1215072961793188046L;

	public PassException() {
		super(503, "Login with USER first.");
	}

}
