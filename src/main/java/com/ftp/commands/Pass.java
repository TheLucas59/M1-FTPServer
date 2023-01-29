package com.ftp.commands;

import com.ftpserver.exceptions.CommandException;

/**
 * Pass is used to send and check the password of the user
 * @author Aurélien Plancke
 *
 */
public class Pass extends Command{

	private final String EXPECTED_PASSWORD = "anonymous";
	
	public Pass(int code, String message) {
		super(code, message);
	}

	/**
	 * Check the given password in the client request
	 * @return True if the password send by the user is equals to the one in server
	 */
	@Override
	public boolean handleRequest() throws CommandException {
		if(this.EXPECTED_PASSWORD.equals(this.message))
			return true;
		return false;
	}

}
