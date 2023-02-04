package com.ftpserver.exceptions;

public class FailedChangeDirectory extends CommandException {

	private static final long serialVersionUID = 734101241501502973L;

	public FailedChangeDirectory() {
		super(550, "Failed to change directory.");
	}

}
