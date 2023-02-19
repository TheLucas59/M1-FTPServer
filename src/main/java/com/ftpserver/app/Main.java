package com.ftpserver.app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.util.threads.ClientThread;

/**
 * Main class that runs the server and wait for new connections
 * 
 * @author Lucas Plé, Aurélien Plancke
 */
public class Main {

	private static final Log LOGGER = LogFactory.getLog(Main.class);

	private static List<ClientThread> openClients = new ArrayList<>();

	public static void main(String[] args) {

		Object syncronizer = new Object();
		int port = Integer.parseInt(args[0]);
		String authentification = args[2];
		String[] userAndPassword = authentification.split(":");
		if(userAndPassword.length != 2) {
			LOGGER.error("Please specify an user and a password like this <user:password>.");
			System.exit(1);
		}
		
		String root = args[1];
		Path rootPath = Paths.get(root);
		if (Files.notExists(rootPath)) {
			try {
				Files.createDirectory(rootPath);
			} catch (IOException e) {
				LOGGER.error("Cannot create the directory that will be used for the FTP server", e);
				System.exit(1);
			}
		} else {
			if (!Files.isDirectory(rootPath)) {
				LOGGER.error("Path given in parameter is not a directory");
				System.exit(1);
			}
		}

		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

		while (true) {
			System.out.println("Waiting a client ...");
			try {
				Socket socket = server.accept();

				openNewClient(socket, rootPath, syncronizer, userAndPassword);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void openNewClient(Socket socket, Path rootPath, Object syncronizer, String[] userAndPassword) {
		ClientThread client = new ClientThread(socket, openClients, rootPath, syncronizer, userAndPassword[0], userAndPassword[1]);
		openClients.add(client);
		client.start();
	}
}