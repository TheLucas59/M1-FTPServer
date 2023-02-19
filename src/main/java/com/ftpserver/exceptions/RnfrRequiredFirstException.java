package com.ftpserver.exceptions;

/**
 * Exception thrown if the user calls the RNTO command before calling the RNFR command.
 * @author Lucas Plé
 *
 */
public class RnfrRequiredFirstException extends CommandException {

	private static final long serialVersionUID = -2999540508531345763L;

	public RnfrRequiredFirstException() {
		super(503, "RNFR required first.");
	}
}
