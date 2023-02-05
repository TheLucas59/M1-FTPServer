package com.ftpserver.exceptions;

public class RemoveDirectoryFailedException extends CommandException {

	private static final long serialVersionUID = -2546254515940497552L;

	public RemoveDirectoryFailedException() {
		super(550, "Remove directory operation failed");
	}
}
