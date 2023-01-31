package com.util.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ftp.commands.CommandHandler;
import com.ftpserver.exceptions.CommandException;
import com.util.SocketUtils;

/**
 * Class that creates a Thread when a new client connects to the server
 * @author Lucas Plé
 *
 */
public class ClientThread extends Thread {
	
	private static final Log LOGGER = LogFactory.getLog(ClientThread.class);
	
	private static final String CONNECTION_SUCCESSFULL = "220";
	private Socket client;
	private List<ClientThread> allClients;
	
	public ClientThread(Socket client, List<ClientThread> allClients) {
		this.client = client;
		this.allClients = allClients;
	}
	
	@Override
	public void run() {
		try {
			BufferedReader reader = SocketUtils.getReadableInputStream(this.client);
			PrintWriter writer = SocketUtils.getWritableOutputStream(this.client);
			
			SocketUtils.sendMessageWithFlush(writer, CONNECTION_SUCCESSFULL);
			String request = "";
			while((request = reader.readLine()) != null) {
				try {
					CommandHandler.handleCommand(request);
					LOGGER.info(request);
				} catch (CommandException e) {
					e.printStackTrace();
				}
			}
			
			LOGGER.info("User disconnected");
			this.allClients.remove(this);
			Thread.currentThread().interrupt();
		}
		catch(IOException ioe) {
			LOGGER.error(ioe);
		}
	}
}
