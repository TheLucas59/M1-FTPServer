package com.ftpserver.exceptions;

/**
 * Exception thrown when a user try to use PASS before using USER
 * @author Lucas Plé
 *
 */
public class LoginWithUserFirstException extends CommandException {

	private static final long serialVersionUID = -5290805674916969774L;

	public LoginWithUserFirstException() {
		super(503, "Login with USER first.");
	}

}
