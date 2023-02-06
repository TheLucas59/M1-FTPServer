package com.ftpserver.exceptions;

public class PasvNeededException extends CommandException {

	private static final long serialVersionUID = 2111043839286274367L;

	public PasvNeededException() {
		super(425, "Use PASV first.");
	}

}
