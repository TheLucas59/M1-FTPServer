package com.ftpserver.exceptions;

/**
 * Exception thrown when the server couldn't retrieve a file, it can happen when a file no longer exist, or from an IO Exception
 * @author Aurélien Plancke
 *
 */
public class RetrFailedException extends CommandException {
	
	private static final long serialVersionUID = -3815953776880651064L;

	public RetrFailedException() {
		super(421, "RETR command failed");
	}
}
