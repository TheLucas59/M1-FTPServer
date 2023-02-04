package com.ftp.commands;

import com.ftpserver.exceptions.CommandException;
import com.ftpserver.exceptions.CommandNotFoundException;
import com.ftpserver.exceptions.NoChangeFromGuestUserException;
import com.ftpserver.exceptions.UserAlreadyLoggedInException;

/**
 * Utility class used to parse user input and execute the adequate command.
 * @author Lucas Plé, Aurélien Plancke
 *
 */
public class CommandHandler {

	public static void handleCommand(String request) throws CommandException {
		String[] input = new String[2];
		parseInput(request, input);
		String command = input[0].toUpperCase();
		String param = input[1];
		
		Command commandExecutable = null;
		switch(command){
			case CommandConstant.USER :
				throw new NoChangeFromGuestUserException();
			case CommandConstant.PASS :
				throw new UserAlreadyLoggedInException();
			
		default:
			throw new CommandNotFoundException();
		}
		//System.out.println(commandExecutable.handleRequest());
	}
	
	public static void parseInput(String request, String[] input) {
		String[] commandAndParam = request.split(" ");
		input[0] = commandAndParam[0];
		if(commandAndParam.length > 1) {
			input[1] = commandAndParam[1];
		}
	}
}
