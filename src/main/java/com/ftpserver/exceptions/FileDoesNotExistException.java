package com.ftpserver.exceptions;

/**
 * Exception thrown when the server couldn't access a file, most of time it is because the file is no longer there
 * @author Aurélien Plancke
 *
 */
public class FileDoesNotExistException extends CommandException {
	
	private static final long serialVersionUID = 541841955801273605L;

	public FileDoesNotExistException() {
		super(550, "The system cannot find the file spcified");
	}
}
