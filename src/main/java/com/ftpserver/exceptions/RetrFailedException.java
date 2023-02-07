package com.ftpserver.exceptions;

public class RetrFailedException extends CommandException {
	
	private static final long serialVersionUID = -3815953776880651064L;

	public RetrFailedException() {
		super(421, "RETR command failed");
	}
}
