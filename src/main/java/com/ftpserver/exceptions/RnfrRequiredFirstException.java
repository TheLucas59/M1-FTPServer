package com.ftpserver.exceptions;

public class RnfrRequiredFirstException extends CommandException {

	private static final long serialVersionUID = -2999540508531345763L;

	public RnfrRequiredFirstException() {
		super(503, "RNFR required first.");
	}
}
