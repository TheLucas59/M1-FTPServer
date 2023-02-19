package com.ftp.commands;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.ftpserver.exceptions.CommandException;
import com.ftpserver.exceptions.RnfrFailedException;
import com.util.threads.ClientThread;

/**
 * Rnfr Specify the file that will be renamed or moved
 * @author Lucas Plé
 *
 */
public class Rnfr extends Command {

	private ClientThread client;
	private String fileName;

	public Rnfr(PrintWriter writer, ClientThread client, String fileName) {
		super(writer);
		this.successCode = 350;
		this.successPhrase = "Ready for RNTO.";
		this.client = client;
		this.fileName = fileName;
	}

	@Override
	protected boolean handleRequest() throws CommandException {
		String path = this.client.getCurrentPath().toString();
		if(!path.endsWith("/")) {
			path = path.concat("/");
		}
		Path fileToRename = Paths.get(path.concat(this.fileName));
		if(Files.exists(fileToRename)) {
			this.client.setPathToRename(fileToRename);
		}
		else {
			throw new RnfrFailedException();
		}
		return true;
	}

}
