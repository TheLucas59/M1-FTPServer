package com.ftp.commands;

import java.io.PrintWriter;

import com.ftpserver.exceptions.CommandException;
import com.util.threads.ClientThread;

/**
 * Pwd return the current path where the client is located
 * @author Lucas Plé
 *
 */
public class Pwd extends Command {
	
	private ClientThread client;
	
	public Pwd(PrintWriter writer, ClientThread client) {
		super(writer);
		this.successCode = 257;
		this.client = client;
	}

	@Override
	protected boolean handleRequest() throws CommandException {
		String pathString = this.client.getCurrentPath().toString();
		String rootString = this.client.getRootPath().toString();
		String replacement = "";
		if(pathString.equals(rootString)) {
			replacement = "/";
		}
		pathString = pathString.replace(rootString, replacement);
		this.successPhrase = "\"" + pathString + "\" is the current directory";
		return true;
	}

}
