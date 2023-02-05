package com.ftpserver.exceptions;

public class FileDoesNotExistException extends CommandException {
	
	private static final long serialVersionUID = 541841955801273605L;

	public FileDoesNotExistException() {
		super(550, "The system cannot find the file spcified");
	}
}
