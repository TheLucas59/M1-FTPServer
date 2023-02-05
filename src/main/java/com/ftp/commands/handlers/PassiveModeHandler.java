package com.ftp.commands.handlers;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Utility class used to manage the passive mode on the server
 * @author Lucas Plé
 *
 */
public class PassiveModeHandler {
	
	private PassiveModeHandler() {}

	/**
	 * Provide a server socket to give the client a port. This port will be used to transfer data.
	 * @return A socket on which the server can wait a new connection for the data canal.
	 * @throws IOException
	 */
	public static ServerSocket enterPassiveMode() throws IOException {
		return new ServerSocket(0);
	}
	
}