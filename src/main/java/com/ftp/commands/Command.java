package com.ftp.commands;

import java.io.PrintWriter;

import com.ftpserver.exceptions.CommandException;
import com.util.SocketUtils;

/**
 * Abstract class that defines the common behavior of FTP server side commands
 * @author Aurélien Plancke, Lucas Plé
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
	 * Run a command and write his output on client
	 * @return True on success, False on fail
	 * @throws CommandException the specific exception to the command
	 */
	public boolean run() throws CommandException {
		if(this.handleRequest()) {
			this.writeSuccess();
			return true;
		}
		return false;
	}
	/**
	 * Implements the specific behavior of each commands
	 * @return True if the command returned successfully
	 * @throws CommandException on error, see specific command for error cases
	 */
	protected abstract boolean handleRequest() throws CommandException;
	
	/**
	 * Write the result of the command on the client socket
	 */
	protected void writeSuccess() {
		StringBuilder response = new StringBuilder();
		response.append(this.successCode);
		response.append(" ");
		response.append(this.successPhrase);
		SocketUtils.sendMessageWithFlush(this.writer, response.toString());
	}
	
}
