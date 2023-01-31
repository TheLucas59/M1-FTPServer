package com.ftpserver.app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.util.threads.ClientThread;

/**
 * Hello world!
 *
 */
public class Main {
	
	private static List<ClientThread> openClients = new ArrayList<>();
	
    public static void main(String[] args) {
    	
    	ServerSocket server = null;
    	try {
			server = new ServerSocket(Integer.parseInt(args[0]));
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
    	
    	while(true) {
    		System.out.println("Waiting a client ...");
    		try {
				Socket socket = server.accept();
				
				openNewClient(socket);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
    
    public static void openNewClient(Socket socket) {
    	ClientThread client = new ClientThread(socket, openClients);
    	openClients.add(client);
    	client.start();
    }   
}