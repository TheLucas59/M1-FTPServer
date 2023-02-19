package com.ftpserver.exceptions;

/**
 * Exception thrown when a directory couldn't be created, it can occur if there is an IO Exception or if the user doesn't have the right to do so
 * @author Aurélien Plancke
 *
 */
public class CreateDirectoryFailedException extends CommandException {

	private static final long serialVersionUID = 7157045829123288619L;

	public CreateDirectoryFailedException() {
		super(550, "Create directory operation failed");
	}
}
