package com.ftpserver.exceptions;

public class RnfrFailedException extends CommandException {

	private static final long serialVersionUID = -2039023928106596559L;

	public RnfrFailedException() {
		super(550, "RNFR command failed.");
	}
	
	

}
