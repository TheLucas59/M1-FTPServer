package com.ftp.commands;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.ftpserver.exceptions.CommandException;
import com.ftpserver.exceptions.FileDoesNotExistException;
import com.util.threads.ClientThread;

/**
 * Dele is used to delete a file
 * @author Lucas Plé
 *
 */
public class Dele extends Command {
	
	private ClientThread client;
	private String fileToDelete;
	private Object synchronizer;
	
	public Dele(PrintWriter writer, ClientThread client, String fileToDelete, Object synchronizer) {
		super(writer);
		this.client = client;
		this.fileToDelete = fileToDelete;
		this.successCode = 250;
		this.successPhrase = "Delete operation successful.";
		this.synchronizer = synchronizer;
	}

	@Override
	protected boolean handleRequest() throws CommandException {
		String pathFileDelimiter = "";
		if(!this.client.getCurrentPath().toString().endsWith("/")) {
			pathFileDelimiter = "/";
		}
		
		Path delete = Paths.get(this.client.getCurrentPath().toString() + pathFileDelimiter + this.fileToDelete);
		synchronized(this.synchronizer) {
			try {
				Files.delete(delete);
			} catch (IOException e) {
				throw new FileDoesNotExistException();
			}
		}
		return true;
	}

}
