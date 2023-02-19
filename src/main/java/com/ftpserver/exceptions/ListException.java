package com.ftpserver.exceptions;

/**
 * Exception thrown when the server couldn't list the content of the current directory
 * @author Aurélien Plancke
 *
 */
public class ListException extends CommandException {

	private static final long serialVersionUID = 5998246309433258698L;

	public ListException() {
		super(550, "Requested action not taken.");
	}

}
