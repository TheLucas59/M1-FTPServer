package com.ftpserver.exceptions;

/**
 * Exception thrown when a user tries to use any command on the server before authenticating himself.
 * @author Lucas Plé
 *
 */
public class AuthenticateFirstException extends CommandException {

	private static final long serialVersionUID = 7630036735600283411L;

	public AuthenticateFirstException() {
		super(530, "Please login with USER and PASS.");
	}
}
