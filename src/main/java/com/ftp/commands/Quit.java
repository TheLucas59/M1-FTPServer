package com.ftp.commands;

import java.io.IOException;
import java.io.PrintWriter;

import com.ftpserver.exceptions.CommandException;
import com.util.threads.ClientThread;

/**
 * Quit exit the client connection.
 * @author Lucas Plé
 *
 */
public class Quit extends Command {
	
	private ClientThread client;
	
	public Quit(PrintWriter writer, ClientThread client) {
		super(writer);
		this.client = client;
		this.successCode = 221;
		this.successPhrase = "Goodbye.";
	}

	@Override
	protected boolean handleRequest() throws CommandException {
		this.client.getAllClients().remove(this.client);
		this.writeSuccess();
		try {
			closeEverything();
		} catch (IOException e) {
			this.client.interrupt();
		}
		return false;
	}
	
	private void closeEverything() throws IOException {
		if(this.client.getDataCanal() != null) {
			this.client.getDataCanal().close();
		}
		this.client.getClient().close();
		synchronized(this.client.getSynchronizer()) {
			this.client.getSynchronizer().notifyAll();
		}
		Thread.currentThread().interrupt();
	}

}
