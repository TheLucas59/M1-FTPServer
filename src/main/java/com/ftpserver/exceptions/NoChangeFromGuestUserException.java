package com.ftpserver.exceptions;

/**
 * Exception thrown when user tries to use USER a second time whereas the server is anonymous only.
 * @author Lucas Plé
 *
 */
public class NoChangeFromGuestUserException extends CommandException {

	private static final long serialVersionUID = -1515334143398162032L;

	public NoChangeFromGuestUserException() {
		super(530, "Can't change from guest user.");
	}
}
