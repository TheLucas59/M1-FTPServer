package com.ftpserver.exceptions;

/**
 * Exception thrown in the RNFR command if the file the client wants to rename doesn't exist.
 * @author Lucas Plé
 *
 */
public class RnfrFailedException extends CommandException {

	private static final long serialVersionUID = -2039023928106596559L;

	public RnfrFailedException() {
		super(550, "RNFR command failed.");
	}
	
	

}
