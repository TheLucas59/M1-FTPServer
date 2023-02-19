package com.ftp.commands;

import java.io.PrintWriter;

import com.ftpserver.exceptions.CommandException;

/**
 * Type is used to enable binary transfert mode
 * @author Aurélien Plancke
 *
 */
public class Type extends Command {
	
	public Type(PrintWriter writer) {
		super(writer);
		this.successCode = 200;
		this.successPhrase = "Switching to Binary mode.";
	}

	@Override
	protected boolean handleRequest() throws CommandException {
		return true;
	}

}
