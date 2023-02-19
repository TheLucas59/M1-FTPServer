package com.ftp.commands;

import java.io.PrintWriter;

import com.ftpserver.exceptions.CommandException;
import com.ftpserver.exceptions.PassException;

/**
 * Pass is used to send and check the password of the user
 * @author Aurélien Plancke, Lucas Plé
 *
 */
public class Pass extends Command {
	
	private String password;
	private String expectedPassword;
	private String user;
	private String expectedUser;
	
	public Pass(PrintWriter writer, String user, String expectedUser, String password, String expectedPassword) {
		super(writer);
		this.password = password;
		this.expectedPassword = expectedPassword;
		this.user = user;
		this.expectedUser = expectedUser;
		this.successCode = 230;
		this.successPhrase = "Login successful.";
	}

	/**
	 * Check the given password in the client request
	 * @return True if the password send by the user is equals to the one in server
	 */
	@Override
	protected boolean handleRequest() throws CommandException {
		if(this.expectedPassword.equals(password) && this.expectedUser.equals(user)) {
			return true;
		}
		throw new PassException();
	}

}
