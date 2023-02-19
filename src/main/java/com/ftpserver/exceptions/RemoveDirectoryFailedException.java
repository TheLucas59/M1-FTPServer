package com.ftpserver.exceptions;

/**
 * Exception thrown when the client couldn't remove directory, most of the time this exception is thrown when a user doesn't have the right access, or if the directory is no longer there
 * @author Aurélien Plancke
 *
 */
public class RemoveDirectoryFailedException extends CommandException {

	private static final long serialVersionUID = -2546254515940497552L;

	public RemoveDirectoryFailedException() {
		super(550, "Remove directory operation failed");
	}
}
