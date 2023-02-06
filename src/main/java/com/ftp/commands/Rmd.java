package com.ftp.commands;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.ftpserver.exceptions.CommandException;
import com.ftpserver.exceptions.RemoveDirectoryFailedException;
import com.util.threads.ClientThread;

public class Rmd extends Command {

	private ClientThread client;
	private String directory;
	
	public Rmd(PrintWriter writer, ClientThread client, String directory) {
		super(writer);
		this.client = client;
		this.directory = directory;
		this.successCode = 250;
		this.successPhrase = "Remove directory operation successful.";
	}

	@Override
	protected boolean handleRequest() throws CommandException {
		String pathFileDelimiter = "";
		if(!this.client.getCurrentPath().toString().endsWith("/")) {
			pathFileDelimiter = "/";
		}
		
		Path newDirectory = Paths.get(this.client.getCurrentPath().toString() + pathFileDelimiter + this.directory);
		if(Files.exists(newDirectory)) {
			try {
				Files.delete(newDirectory);
			} catch (IOException e) {
				throw new RemoveDirectoryFailedException();
			}
		}
		else {
			throw new RemoveDirectoryFailedException();
		}
		return true;
	}

}
