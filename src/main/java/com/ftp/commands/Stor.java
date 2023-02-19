package com.ftp.commands;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.ftpserver.exceptions.CommandException;
import com.ftpserver.exceptions.StorFailedException;
import com.util.SocketUtils;
import com.util.threads.ClientThread;

/**
 * Stor Store a file from client to server
 * @author Lucas Plé Aurélien Plancke
 *
 */
public class Stor extends Command {

	private ClientThread client;
	private String fileName;
	private int readyCode = 150;
	private String readyPhrase = "Ok to send data.";
	private Object syncronizer;

	public Stor(PrintWriter writer, ClientThread client, String fileName, Object syncronizer) {
		super(writer);
		this.successCode = 226;
		this.successPhrase = "Transfer complete.";
		this.client = client;
		this.fileName = fileName;
		this.syncronizer = syncronizer;
	}

	@Override
	protected boolean handleRequest() throws CommandException {
		this.writeReady();
		try {
			this.writeFile();
		} catch (IOException e) {
			throw new StorFailedException();
		}
		return true;
	}

	private void writeReady() {
		StringBuilder response = new StringBuilder();
		response.append(this.readyCode);
		response.append(" ");
		response.append(this.readyPhrase);
		SocketUtils.sendMessageWithFlush(this.writer, response.toString());
	}

	private void writeFile() throws IOException {
		Socket dataSocket = this.client.getDataCanal().accept();
		InputStream stream = dataSocket.getInputStream();
		byte[] fileRead = stream.readAllBytes();
		String pathFileDelimiter = "";
		if(!this.client.getCurrentPath().toString().endsWith("/")) {
			pathFileDelimiter = "/";
		}

		Path newFilePath = Paths.get(this.client.getCurrentPath().toString() + pathFileDelimiter + this.fileName);

		synchronized(this.syncronizer){
			if(Files.notExists(newFilePath)) {
				Files.createFile(newFilePath);
			}
			Files.write(newFilePath, fileRead);
		}
		stream.close();
		dataSocket.close();
		this.client.getDataCanal().close();
		this.client.setDataCanal(null);
	}
}
