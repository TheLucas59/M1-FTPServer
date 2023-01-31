package com.ftp.commands;

import java.io.PrintWriter;

import com.ftpserver.exceptions.CommandException;
import com.util.SocketUtils;

/**
 * Abstract class that defines the common behavior of FTP server side commands
 * @author Aurélien
 *
 */
public abstract class Command {
	
	protected int successCode;
	protected String successPhrase;

	protected PrintWriter writer;
	
	protected Command(PrintWriter writer) {
		this.writer = writer;
	}
	
	/**
	 * 
	 * @return True if the command returned successfully
	 * @throws CommandException on error, see specific command for error cases
	 */
	public abstract boolean handleRequest() throws CommandException;
	
	public void writeSuccess() {
		StringBuilder response = new StringBuilder();
		response.append(this.successCode);
		response.append(" ");
		response.append(this.successPhrase);
		SocketUtils.sendMessageWithFlush(this.writer, response.toString());
	}
	
	
}
