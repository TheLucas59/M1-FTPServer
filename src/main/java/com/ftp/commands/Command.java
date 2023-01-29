package com.ftp.commands;

import com.ftpserver.exceptions.CommandException;

/**
 * Abstract class that defines the common behavior of FTP server side commands
 * @author Aurélien
 *
 */
public abstract class Command {
	
	protected int code;
	protected String message;
	
	public Command(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
	
	/**
	 * 
	 * @return True if the command returned successfully
	 * @throws CommandException on error, see specific command for error cases
	 */
	public abstract boolean handleRequest() throws CommandException;
	
	
	/**
	 * 
	 * @return The response that will be send to the client
	 */
	public String constructResponse() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.code);
		sb.append(" ");
		sb.append(this.message);
		return sb.toString();
	}
}
