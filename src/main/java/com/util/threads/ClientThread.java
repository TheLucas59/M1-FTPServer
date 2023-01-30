package com.util.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
	
	public ClientThread(Socket client) {
		this.client = client;
	}
	
	@Override
	public void run() {
		try {
			BufferedReader reader = SocketUtils.getReadableInputStream(this.client);
			PrintWriter writer = SocketUtils.getWritableOutputStream(this.client);
			
			SocketUtils.sendMessageWithFlush(writer, CONNECTION_SUCCESSFULL);
			
			while(!this.client.isClosed()) {
				String command = reader.readLine();
				
				LOGGER.info(command);
			}
			
			LOGGER.info("User disconnected");
			this.stop();
		}
		catch(IOException ioe) {
			LOGGER.error(ioe);
		}
	}
}
