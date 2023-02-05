package com.ftpserver.exceptions;

public class CreateDirectoryFailedException extends CommandException {

	private static final long serialVersionUID = 7157045829123288619L;

	public CreateDirectoryFailedException() {
		super(550, "Create directory operation failed");
	}
}
