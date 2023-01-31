package com.ftp.commands;

import java.util.ArrayList;
import java.util.List;

import com.ftpserver.exceptions.CommandException;
import com.ftpserver.exceptions.CommandNotFoundException;

public class CommandHandler {

	
	public static void handleCommand(String request) throws CommandException {
		String[] command = request.split(" ");
		List<String> params = new ArrayList<>();
		Command commandExecutable = null;
		for(int i = 1; i < command.length; i++) {
			params.add(command[i]);
		}
		switch(command[0]){
		case CommandConstant.USER:
			commandExecutable = new User(0, params);
			break;
		case CommandConstant.PASS:
			commandExecutable = new Pass(0, params);
			break;
		default:
			throw new CommandNotFoundException();
		}
		System.out.println(commandExecutable.handleRequest());
	}
}
