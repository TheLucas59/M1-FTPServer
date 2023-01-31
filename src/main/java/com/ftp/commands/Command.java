package com.ftp.commands;

import java.util.List;

import com.ftpserver.exceptions.CommandException;

/**
 * Abstract class that defines the common behavior of FTP server side commands
 * @author Aurélien
 *
 */
public abstract class Command {
	
	protected int code;
	protected List<String> params;
	
	public Command(int code, List<String> params) {
		this.code = code;
		this.params = params;
	}
	
	public int getCode() {
		return code;
	}
	
	public List<String> getMessage() {
		return params;
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
		return sb.toString();
	}
	
	
}
