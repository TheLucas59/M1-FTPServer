package com.ftp.commands;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.ftpserver.exceptions.CommandException;
import com.ftpserver.exceptions.FailedChangeDirectory;
import com.util.threads.ClientThread;

public class Cwd extends Command {

	private ClientThread client;
	private String pathToMove;
	
	public Cwd(PrintWriter writer, ClientThread client, String pathToMove) {
		super(writer);
		this.successCode = 250;
		this.successPhrase = "Directory successfully changed.";
		this.client = client;
		this.pathToMove = pathToMove;
	}
	
	@Override
	protected boolean handleRequest() throws CommandException {
		String currentPathString = this.client.getCurrentPath().toString();
		if(!this.pathToMove.startsWith("/")) {
			currentPathString = currentPathString + "/";
		}
		currentPathString = currentPathString + this.pathToMove;
		Path newPath = Paths.get(currentPathString);
		if(Files.exists(newPath) && Files.isDirectory(newPath)) {
			this.client.setCurrentPath(newPath);
		}
		else {
			throw new FailedChangeDirectory();
		}
		return true;
	}

}
