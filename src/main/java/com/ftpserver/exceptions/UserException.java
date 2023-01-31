package com.ftpserver.exceptions;

/**
 * User exception is the exception returned when TODO insert cases of error here]
 * @author Aurélien Plancke
 *
 */
public class UserException extends CommandException {
	
	private static final long serialVersionUID = -6334423423908437219L;

	public UserException() {
		super(530, "This FTP server is anonymous only.");
	}

}
