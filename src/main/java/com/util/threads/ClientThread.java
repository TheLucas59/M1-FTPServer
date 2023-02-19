package com.util.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ftp.commands.CommandConstant;
import com.ftp.commands.handlers.AuthenticationHandler;
import com.ftp.commands.handlers.CommandHandler;
import com.ftpserver.exceptions.CommandException;
import com.util.SocketUtils;

/**
 * Class that creates a Thread when a new client connects to the server
 * @author Lucas Plé
 *
 */
public class ClientThread extends Thread {
	
	private static final Log LOGGER = LogFactory.getLog(ClientThread.class);
	
	private static final String CONNECTION_SUCCESSFULL = "220 FTP server (vsftpd)";
	private Socket client;
	private List<ClientThread> allClients;
	private boolean connected = false;
	private Path rootPath;
	private Path currentPath;
	private Path pathToRename;
	private ServerSocket dataCanal;
	private Object synchronizer;
	
	public ClientThread(Socket client, List<ClientThread> allClients, Path rootPath, Object synchronizer) {
		this.client = client;
		this.allClients = allClients;
		this.currentPath = rootPath;
		this.rootPath = rootPath;
		this.synchronizer = synchronizer;
	}
	
	@Override
	public void run() {
		try {
			BufferedReader reader = SocketUtils.getReadableInputStream(this.client);
			PrintWriter writer = SocketUtils.getWritableOutputStream(this.client);
			
			SocketUtils.sendMessageWithFlush(writer, CONNECTION_SUCCESSFULL);
			
			String request = "";
			while(!connected) {
				request = reader.readLine();
				String[] input = new String[2];
				CommandHandler.parseInput(request, input);
				String command = input[0];
				String param = input[1];
				
				if(CommandConstant.USER.equalsIgnoreCase(command)) {
					authenticate(reader, writer, param);
				}
				else if(CommandConstant.PASS.equalsIgnoreCase(command)) {
					throwUserFirst(writer);
				}
				else {
					throwAuthenticateFirst(writer);
				}
			}
			
			while((request = reader.readLine()) != null) {
				try {
					if(!request.isBlank() && !request.isEmpty()) {
						LOGGER.info(request);
						CommandHandler.handleCommand(request, writer, this, this.synchronizer);
					}
				} catch (CommandException e) {
					SocketUtils.sendMessageWithFlush(writer, e.toString());
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

	private void authenticate(BufferedReader reader, PrintWriter writer, String param) throws IOException {
		try {
			this.connected = AuthenticationHandler.connect(param, writer, reader);
		}
		catch(CommandException e) {
			SocketUtils.sendMessageWithFlush(writer, e.toString());
		}
	}
	
	private void throwUserFirst(PrintWriter writer) {
		try {
			AuthenticationHandler.throwLoginWithUserFirst();
		}
		catch(CommandException e) {
			SocketUtils.sendMessageWithFlush(writer, e.toString());
		}
	}
	
	private void throwAuthenticateFirst(PrintWriter writer) {
		try {
			AuthenticationHandler.throwAuthenticateFirst();
		}
		catch(CommandException e) {
			SocketUtils.sendMessageWithFlush(writer, e.toString());
		}
	}

	public Path getRootPath() {
		return rootPath;
	}

	public Path getCurrentPath() {
		return currentPath;
	}

	public void setCurrentPath(Path currentPath) {
		this.currentPath = currentPath;
	}
	
	public ServerSocket getDataCanal() {
		return dataCanal;
	}

	public void setDataCanal(ServerSocket dataCanal) {
		this.dataCanal = dataCanal;
	}

	public Path getPathToRename() {
		return pathToRename;
	}

	public void setPathToRename(Path pathToRename) {
		this.pathToRename = pathToRename;
	}
}
