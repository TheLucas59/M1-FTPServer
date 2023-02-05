package com.ftp.commands;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.ftpserver.exceptions.CommandException;
import com.ftpserver.exceptions.FileDoesNotExistException;
import com.util.threads.ClientThread;

public class Dele extends Command {
	
	private ClientThread client;
	private String fileToDelete;
	
	public Dele(PrintWriter writer, ClientThread client, String fileToDelete) {
		super(writer);
		this.client = client;
		this.fileToDelete = fileToDelete;
		this.successCode = 250;
		this.successPhrase = "Delete operation successful.";
	}

	@Override
	protected boolean handleRequest() throws CommandException {
		if(!this.client.getCurrentPath().toString().endsWith("/")) {
			this.client.setCurrentPath(Paths.get(this.client.getCurrentPath().toString() + "/"));
		}
		Path delete = Paths.get(this.client.getCurrentPath().toString() + this.fileToDelete);
		try {
			Files.deleteIfExists(delete);
		} catch (IOException e) {
			throw new FileDoesNotExistException();
		}
		return true;
	}

}
