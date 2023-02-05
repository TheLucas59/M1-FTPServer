package com.ftpserver.exceptions;

public class StorFailedException extends CommandException {
	
	private static final long serialVersionUID = -1861922711538242965L;

	public StorFailedException() {
		super(421, "STOR command failed");
	}
}
