package com.ftp.commands.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.ftp.commands.Command;
import com.ftp.commands.CommandConstant;
import com.ftp.commands.Pass;
import com.ftp.commands.User;
import com.ftpserver.exceptions.AuthenticateFirstException;
import com.ftpserver.exceptions.CommandException;
import com.ftpserver.exceptions.LoginWithUserFirstException;
import com.ftpserver.exceptions.PassException;

/**
 * Utility class used to handle the different connection events.
 * @author Lucas Plé
 *
 */
public class AuthenticationHandler {
	
	public static boolean connect(String login, PrintWriter writer, BufferedReader reader) throws IOException, CommandException {
		Command user = new User(writer, login);
		if(user.run()) {
			String passLine = reader.readLine();
			String[] commandAndParam = passLine.split(" ");
			String command = commandAndParam[0];
			if(CommandConstant.PASS.equals(command)) {
				String password = "";
				if(commandAndParam.length > 1) {
					password = commandAndParam[1];
				}
				
				Command pass = new Pass(writer, password);
				return pass.run();
			}
			throw new PassException();
		}
		
		return false;
	}
	
	public static void throwLoginWithUserFirst() throws CommandException {
		throw new LoginWithUserFirstException();
	}

	public static void throwAuthenticateFirst() throws CommandException {
		throw new AuthenticateFirstException();
	}
}
