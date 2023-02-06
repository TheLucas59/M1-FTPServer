package com.ftp.commands;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.ftpserver.exceptions.CommandException;
import com.ftpserver.exceptions.CreateDirectoryFailedException;
import com.util.threads.ClientThread;

public class Mkd extends Command {
	
	private ClientThread client;
	private String directory;
	
	public Mkd(PrintWriter writer, ClientThread client, String directory) {
		super(writer);
		this.client = client;
		this.directory = directory;
		this.successCode = 257;
	}

	@Override
	protected boolean handleRequest() throws CommandException {
		String pathFileDelimiter = "";
		String pathString = this.client.getCurrentPath().toString();
		if(!pathString.endsWith("/")) {
			pathFileDelimiter = "/";
		}
		Path newDirectory = Paths.get(pathString + pathFileDelimiter + this.directory);
		if(Files.notExists(newDirectory)) {
			try {
				Files.createDirectory(newDirectory);
			} catch (IOException e) {
				throw new CreateDirectoryFailedException();
			}
		}
		else {
			throw new CreateDirectoryFailedException();
		}
		String replacement = "";
		String endWith = "";
		if(this.client.getCurrentPath().equals(this.client.getRootPath())) {
			replacement = "/";
		}else if(!this.client.getCurrentPath().endsWith("/")) {
			endWith = "/";
		}
		pathString = pathString.replace(this.client.getRootPath().toString(), replacement) + endWith +this.directory;
		this.successPhrase = "\"" + pathString + "\" created";
		return true;
	}

}
