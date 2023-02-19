package com.ftp.commands;

import java.io.PrintWriter;
import java.nio.file.Paths;

import com.ftpserver.exceptions.CommandException;
import com.ftpserver.exceptions.UnautorizedException;
import com.util.threads.ClientThread;

/**
 * Cdup is used to go up one level in directory hierarchy.
 * @author Aurélien Plancke
 *
 */
public class Cdup extends Command {

	ClientThread client; 

	public Cdup(PrintWriter writer, ClientThread client) {
		super(writer);
		this.client = client;
		this.successCode = 250;
		this.successPhrase = "Directory successfully changed.";
	}

	@Override
	protected boolean handleRequest() throws CommandException {
		String currentPath = client.getCurrentPath().toString();
		if(currentPath.equals(client.getRootPath().toString())) {
			throw new UnautorizedException();
		}
		
		int index = currentPath.lastIndexOf('/');
		String pathAfterCDUP = currentPath.substring(0,index); 
		this.client.setCurrentPath(Paths.get(pathAfterCDUP));
		
		return true;
	}

}
