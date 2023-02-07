package com.ftp.commands;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.ftpserver.exceptions.CommandException;
import com.ftpserver.exceptions.RetrFailedException;
import com.util.SocketUtils;
import com.util.threads.ClientThread;

public class Retr extends Command {
	
	private ClientThread client;
	private String fileName;
	private int readyCode = 150;
	private String readyPhrase = "Opening BINARY mode data connection for ";
	private Path download;
	
	public Retr(PrintWriter writer, ClientThread client, String fileName) {
		super(writer);
		this.successCode = 226;
		this.successPhrase = "Transfer complete.";
		this.client = client;
		this.fileName = fileName;
	}

	@Override
	protected boolean handleRequest() throws CommandException {
		try {
			this.writeReady();
			this.sendFile();
		} catch (IOException e) {
			throw new RetrFailedException();
		}
		return true;
	}

	private void writeReady() throws IOException {
		StringBuilder response = new StringBuilder();
		response.append(this.readyCode);
		response.append(" ");
		response.append(this.readyPhrase);
		String pathFileDelimiter = "";
		if(!this.client.getCurrentPath().toString().endsWith("/")) {
			pathFileDelimiter = "/";
		}
		
		this.download = Paths.get(this.client.getCurrentPath().toString() + pathFileDelimiter + this.fileName);
		response.append("("); response.append(Files.size(download)); response.append(" bytes)");
		SocketUtils.sendMessageWithFlush(this.writer, response.toString());
	}

	private void sendFile() throws IOException {
		Socket dataSocket = this.client.getDataCanal().accept();
		OutputStream stream = dataSocket.getOutputStream();
		byte[] fileRead = null;
		if(Files.exists(download)) {
			fileRead = Files.readAllBytes(this.download);
		}
		
		stream.write(fileRead);
		stream.flush();
		
		stream.close();
		dataSocket.close();
		this.client.getDataCanal().close();
		this.client.setDataCanal(null);
	}
	
}
