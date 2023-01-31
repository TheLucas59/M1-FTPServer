package com.ftp.commands;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ftpserver.exceptions.CommandException;

/**
 * Pass is used to send and check the password of the user
 * @author Aurélien Plancke
 *
 */
public class Pass extends Command{

	private final String EXPECTED_PASSWORD = "anonymous";
	private static final Log LOGGER = LogFactory.getLog(Pass.class);
	
	public Pass(int code, List<String> params) {
		super(code, params);
		if(this.params.size() != 1) {
			LOGGER.fatal("Number of args not correct");
		}
		
	}

	/**
	 * Check the given password in the client request
	 * @return True if the password send by the user is equals to the one in server
	 */
	@Override
	public boolean handleRequest() throws CommandException {
		if(this.EXPECTED_PASSWORD.equals(this.params.get(0)))
			return true;
		return false;
	}

}
