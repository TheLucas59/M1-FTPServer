package com.ftpserver.exceptions;

public class RntoFailedException extends CommandException {
	
	private static final long serialVersionUID = 948039721209135892L;

	public RntoFailedException() {
		super(550, "Rename failed.");
	}
}
