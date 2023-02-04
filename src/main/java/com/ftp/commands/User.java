package com.ftp.commands;

import java.io.PrintWriter;

import com.ftpserver.exceptions.UserException;

/**
 * User is used to send and check the username of the user
 * @author Aurélien Plancke
 *
 */
public class User extends Command {
	
	private final String EXPECTED_USER = "anonymous";
	
	private String user;

	public User(PrintWriter writer, String user) {
		super(writer);
		this.user = user;
		this.successCode = 331;
		this.successPhrase = "Please specify the password.";
	}

	/**
	 * Check the given username in the client request
	 * @return True if the username send by the user is equals to the one in server
	 */
	@Override
	public boolean handleRequest() throws UserException {
		if(this.EXPECTED_USER.equals(this.user)) {
			this.writeSuccess();
			return true;
		}
		throw new UserException();
	}

}
