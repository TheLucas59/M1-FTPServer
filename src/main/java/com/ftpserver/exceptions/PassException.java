package com.ftpserver.exceptions;

/**
 * Pass exception is the exception returned when the client is not using a valid password or user to authenticate.
 * @author Aurélien Plancke
 *
 */
public class PassException extends CommandException {

	private static final long serialVersionUID = -1215072961793188046L;

	public PassException() {
		super(530, "Login incorrect.");
	}

}
