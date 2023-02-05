package com.ftpserver.exceptions;

/**
 * Exception thrown if there is no available ports for a passive connection on the server
 * @author Lucas Plé
 *
 */
public class PasvFailedException extends CommandException {

	private static final long serialVersionUID = -5783186276119290833L;

	public PasvFailedException() {
		super(421, "PASV command failed");
	}
}
