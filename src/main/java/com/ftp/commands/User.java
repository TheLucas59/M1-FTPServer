package com.ftp.commands;

import com.ftpserver.exceptions.UserException;

/**
 * User is used to send and check the username of the user
 * @author Aurélien Plancke
 *
 */
public class User extends Command {
	
	private final String EXPECTED_USER = "anonymous";

	public User(int code, String message) {
		super(code, message);
	}

	/**
	 * Check the given username in the client request
	 * @return True if the username send by the user is equals to the one in server
	 */
	@Override
	public boolean handleRequest() throws UserException {
		if(this.EXPECTED_USER.equals(message))
			return true;
		return false;
	}

}
