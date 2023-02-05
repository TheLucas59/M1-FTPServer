package com.ftpserver.exceptions;

public class ListException extends CommandException {

	private static final long serialVersionUID = 5998246309433258698L;

	public ListException() {
		super(550, "Requested action not taken.");
	}

}
