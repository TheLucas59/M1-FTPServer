package com.ftpserver.exceptions;


/**
 * Exception thrown when the server couldn't store a file, it is mostly raised from an IO Exception
 * @author Aurélien Plancke
 *
 */
public class StorFailedException extends CommandException {
	
	private static final long serialVersionUID = -1861922711538242965L;

	public StorFailedException() {
		super(421, "STOR command failed");
	}
}
