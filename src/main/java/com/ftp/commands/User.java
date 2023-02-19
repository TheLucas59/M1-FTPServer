package com.ftp.commands;

import java.io.PrintWriter;

import com.ftpserver.exceptions.UserException;
import com.util.threads.ClientThread;

/**
 * User is used to send and check the username of the user
 * @author Aurélien Plancke
 *
 */
public class User extends Command {
	
	private String user;
	private ClientThread client;

	public User(PrintWriter writer, String user, ClientThread client) {
		super(writer);
		this.user = user;
		this.successCode = 331;
		this.successPhrase = "Please specify the password.";
		this.client = client;
	}

	/**
	 * Check the given username in the client request
	 * @return True if the username send by the user is equals to the one in server
	 */
	@Override
	protected boolean handleRequest() throws UserException {
		this.client.setUser(this.user);
		return true;
	}

}
