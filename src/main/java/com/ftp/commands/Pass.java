package com.ftp.commands;

import java.io.PrintWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ftpserver.exceptions.CommandException;
import com.ftpserver.exceptions.PassException;

/**
 * Pass is used to send and check the password of the user
 * @author Aurélien Plancke, Lucas Plé
 *
 */
public class Pass extends Command {
	
	private static final Log LOGGER = LogFactory.getLog(Pass.class);

	private String password;
	
	public Pass(PrintWriter writer, String password) {
		super(writer);
		this.password = password;
		this.successCode = 230;
		this.successPhrase = "Login successful.";
	}

	/**
	 * Check the given password in the client request
	 * @return True if the password send by the user is equals to the one in server
	 */
	@Override
	public boolean handleRequest() throws CommandException {
		if(!this.password.isEmpty()) {
			writeSuccess();
			return true;
		}
		throw new PassException();
	}

}
