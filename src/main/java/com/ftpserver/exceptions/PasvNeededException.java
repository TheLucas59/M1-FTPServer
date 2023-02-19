package com.ftpserver.exceptions;

/**
 * Exception thrown when the client try to use a command that require PASV to have been executed before
 * @author Aurélien Plancke
 *
 */
public class PasvNeededException extends CommandException {

	private static final long serialVersionUID = 2111043839286274367L;

	public PasvNeededException() {
		super(425, "Use PASV first.");
	}

}
