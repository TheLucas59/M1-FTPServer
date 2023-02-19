package com.ftpserver.exceptions;

/**
 * Exception thrown when the user couldn't change directory, most of the time this exception is thrown when a user doesn't have the right access, or if the directory is no longer there
 * @author Aurélien Plancke
 *
 */
public class FailedChangeDirectory extends CommandException {

	private static final long serialVersionUID = 734101241501502973L;

	public FailedChangeDirectory() {
		super(550, "Failed to change directory.");
	}

}
