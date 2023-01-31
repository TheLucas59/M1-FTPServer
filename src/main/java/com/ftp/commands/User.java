package com.ftp.commands;

import java.util.List;

import com.ftpserver.exceptions.UserException;

/**
 * User is used to send and check the username of the user
 * @author Aurélien Plancke
 *
 */
public class User extends Command {
	
	private final String EXPECTED_USER = "anonymous";

	public User(int code, List<String> params) {
		super(code, params);
	}

	/**
	 * Check the given username in the client request
	 * @return True if the username send by the user is equals to the one in server
	 */
	@Override
	public boolean handleRequest() throws UserException {
		if(this.EXPECTED_USER.equals(this.params.get(0)))
			return true;
		return false;
	}

}
