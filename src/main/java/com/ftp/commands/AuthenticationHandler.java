package com.ftp.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.ftpserver.exceptions.CommandException;
import com.ftpserver.exceptions.PassException;
import com.util.SocketUtils;

/**
 * 
 * @author lucas
 *
 */
public class AuthenticationHandler {
	
	public static boolean connect(String login, PrintWriter writer, BufferedReader reader) throws IOException, CommandException {
		Command user = new User(writer, login);
		if(user.handleRequest()) {
			String passLine = reader.readLine();
			String[] commandAndParam = passLine.split(" ");
			String command = commandAndParam[0];
			if(CommandConstant.PASS.equals(command)) {
				String password = "";
				if(commandAndParam.length > 1) {
					password = commandAndParam[1];
				}
				
				Command pass = new Pass(writer, password);
				return pass.handleRequest();
			}
			throw new PassException();
		}
		
		return false;
	}
}
