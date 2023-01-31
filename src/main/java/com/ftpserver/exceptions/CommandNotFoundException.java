package com.ftpserver.exceptions;

public class CommandNotFoundException extends CommandException{

	private static final long serialVersionUID = 1612989100122840281L;

	public CommandNotFoundException() {
		super(502, "Not implemented");
		
	}

}
