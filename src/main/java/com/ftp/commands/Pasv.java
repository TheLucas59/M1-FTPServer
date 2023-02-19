package com.ftp.commands;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

import com.ftp.commands.handlers.PassiveModeHandler;
import com.ftpserver.exceptions.CommandException;
import com.ftpserver.exceptions.PasvFailedException;
import com.util.threads.ClientThread;

/**
 * Pasv is used to get an other socket for data. It return an IP address and a port.
 * @author Lucas Plé
 *
 */
public class Pasv extends Command {
	
	private ClientThread client;

	public Pasv(PrintWriter writer, ClientThread client) {
		super(writer);
		this.client = client;
		this.successCode = 227;
	}
	
	@Override
	protected boolean handleRequest() throws CommandException {
		try {
			client.setDataCanal(PassiveModeHandler.enterPassiveMode());
		} catch (IOException e) {
			throw new PasvFailedException();
		}
		ServerSocket socket = client.getDataCanal();
		int port = socket.getLocalPort();
		String[] addressSplit;
		try {
			addressSplit = InetAddress.getLocalHost().getHostAddress().split("\\.");
		} catch (UnknownHostException e) {
			throw new PasvFailedException();
		}
		String tuple = constructTuple(port, addressSplit);
		this.successPhrase = "Entering Passive Mode " + tuple;
		return true;
	}

	private String constructTuple(int port, String[] addressSplit) {
		StringBuilder tuple = new StringBuilder();
		tuple.append("(");
		for(String number : addressSplit) {
			tuple.append(number);
			tuple.append(",");
		}
		int divided = port / 256;
		int residual = port % 256;
		tuple.append(divided);
		tuple.append(",");
		tuple.append(residual);
		tuple.append(")");
		return tuple.toString();
	}

}
