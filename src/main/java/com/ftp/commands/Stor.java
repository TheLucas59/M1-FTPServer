package com.ftp.commands;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.ftpserver.exceptions.CommandException;
import com.ftpserver.exceptions.StorFailedException;
import com.util.SocketUtils;
import com.util.threads.ClientThread;

public class Stor extends Command {
	
	private ClientThread client;
	private String fileName;
	private int readyCode = 150;
	private String readyPhrase = "Ok to send data.";
	
	public Stor(PrintWriter writer, ClientThread client, String fileName) {
		super(writer);
		this.successCode = 226;
		this.successPhrase = "Transfer complete.";
		this.client = client;
		this.fileName = fileName;
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
		if(Files.notExists(newFilePath)) {
			Files.createFile(newFilePath);
		}
		Files.write(newFilePath, fileRead);

		dataSocket.close();
		this.client.getDataCanal().close();
		this.client.setDataCanal(null);
	}
}
