package com.ftpserver.exceptions;

public class UnautorizedException extends CommandException {

	private static final long serialVersionUID = 6036132866642261099L;

	public UnautorizedException() {
		super(401, "Unauthorized");
	}

	
}
