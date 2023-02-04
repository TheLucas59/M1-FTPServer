package com.ftpserver.exceptions;

/**
 * Exception thrown when user tries to use the PASS command whereas he is already connected anonymously.
 * @author Lucas Plé
 *
 */
public class UserAlreadyLoggedInException extends CommandException {

	private static final long serialVersionUID = -1186760190206804687L;

	public UserAlreadyLoggedInException() {
		super(230, "Already logged in.");
	}
}
