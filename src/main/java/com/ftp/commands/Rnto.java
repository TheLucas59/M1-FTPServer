package com.ftp.commands;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.ftpserver.exceptions.CommandException;
import com.ftpserver.exceptions.RnfrRequiredFirstException;
import com.ftpserver.exceptions.RntoFailedException;
import com.util.threads.ClientThread;

public class Rnto extends Command {
	
	private ClientThread client;
	private Object synchronizer;
	private String pathToRename;
	
	public Rnto(PrintWriter writer, ClientThread client, String pathToRename, Object synchronizer) {
		super(writer);
		this.successCode = 250;
		this.successPhrase = "Rename successful.";
		this.client = client;
		this.synchronizer = synchronizer;
		this.pathToRename = pathToRename;
	}

	@Override
	protected boolean handleRequest() throws CommandException {
		if(this.client.getPathToRename() != null) {
			synchronized(this.synchronizer) {
				if(Files.exists(this.client.getPathToRename())) {
					try {
						Files.move(this.client.getPathToRename(), Paths.get(this.client.getRootPath().toString().concat(this.pathToRename)), StandardCopyOption.REPLACE_EXISTING);
					}
					catch(IOException e) {
						throw new RntoFailedException();
					}
				}
				else {
					throw new RntoFailedException();
				}
			}
		}
		else {
			throw new RnfrRequiredFirstException();
		}
		
		this.client.setPathToRename(null);
		return true;
	}

}
