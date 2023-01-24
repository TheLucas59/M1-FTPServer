package com.ftpserver.app;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class Main {
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
				
				BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				
				os.write("Saluz");
				os.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
}