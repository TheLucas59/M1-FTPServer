package com.ftpserver.exceptions;

/**
 * Abstract class to handle common behavior between commands exception
 * @author Aurélien Plancke
 *
 */
public abstract class CommandException extends Exception{

	private static final long serialVersionUID = 1L;
	public int errorCode;
	public String errorMessage;
	
	public CommandException(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public String toString() {
		return this.errorCode + " " + this.errorMessage;
	}
}
