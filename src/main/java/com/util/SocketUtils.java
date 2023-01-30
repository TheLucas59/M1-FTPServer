package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Utility class used to easily get readable and writable streams from a socket
 * @author Lucas Plé
 *
 */
public class SocketUtils {
	

	/**
	 * Gets the OutputStream from the Socket and create a PrintWriter. This makes you able to send messages
	 * to the connected server
	 * @param s The socket to use
	 * @return A PrintWriter you can use to send messages to the connected server
	 */
	public static PrintWriter getWritableOutputStream(Socket s) throws IOException {
		return new PrintWriter(s.getOutputStream());
	}
	
	/**
	 * Gets the InputStream from the Socket and create a BufferedReader in which you can read messages that
	 * the server sends you.
	 * @param s The socket to use
	 * @return A BufferedReader you can use to read messages coming from the connected server
	 */
	public static BufferedReader getReadableInputStream(Socket s) throws IOException {
		return new BufferedReader(new InputStreamReader(s.getInputStream()));
	}
	
	public static void sendMessageWithFlush(PrintWriter writer, String message) {
		writer.println(message);
		writer.flush();
	}
}
