package com.ftpserver.exceptions;


/**
 * Exception thrown when the user doesn't have the right access to execute a specific command, e.g the user is trying to access files that are above his root path
 * @author Aurélien Plancke
 *
 */
public class UnautorizedException extends CommandException {

	private static final long serialVersionUID = 6036132866642261099L;

	public UnautorizedException() {
		super(401, "Unauthorized");
	}

	
}
