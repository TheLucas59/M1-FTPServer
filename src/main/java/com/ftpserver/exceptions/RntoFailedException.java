package com.ftpserver.exceptions;

/**
 * Exception thrown if the file that is meant to be renamed doesn't exist anymore or if the renaming fails.
 * @author Lucas Plé
 *
 */
public class RntoFailedException extends CommandException {
	
	private static final long serialVersionUID = 948039721209135892L;

	public RntoFailedException() {
		super(550, "Rename failed.");
	}
}
