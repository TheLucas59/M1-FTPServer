package com.ftp.commands;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFileAttributes;

import com.ftpserver.exceptions.CommandException;
import com.ftpserver.exceptions.ListException;
import com.util.SocketUtils;
import com.util.threads.ClientThread;

/**
 * List is used to get the list of files and directories in the current folder
 * @author Aurélien Plancke
 *
 */
public class List extends Command{

	private int readyCode = 150;
	private String readyPhrase = "Here comes the directory listing";
	private ClientThread client;

	protected List(PrintWriter writer, ClientThread client) {
		super(writer);
		this.client = client;
		this.successCode = 150;
		this.successPhrase = "Here comes the directory listing";
	}

	@Override
	protected boolean handleRequest() throws CommandException {
		Socket dataSocket = getDataSocket();
		this.writeReady();
		String response = executeList();
		try {
			PrintWriter dataWriter = SocketUtils.getWritableOutputStream(dataSocket);
			dataWriter.write(response);
		} catch (IOException e) {
			throw new ListException();
		}
		return false;
	}

	/**
	 * 
	 * @param path The path to the file to be analyzed
	 * @return A String that represent a unix-like ls result
	 * @throws IOException
	 */
	private String constructFileString(Path path) throws IOException {
		StringBuilder strb = new StringBuilder();
		
		strb.append(Files.getPosixFilePermissions(path));
		strb.append(" ");
		
		strb.append(Files.getAttribute(path, "unix:nlink"));
		strb.append(" ");
		
		strb.append(Files.getOwner(path).getName());
		strb.append(" ");
		
		strb.append(Files.readAttributes(path, PosixFileAttributes.class, LinkOption.NOFOLLOW_LINKS).group());
		strb.append(" ");
		
		strb.append(Files.size(path));
		strb.append(" ");
		
		strb.append(Files.getLastModifiedTime(path).toString());
		strb.append(" ");
		
		strb.append(path.getFileName());
		strb.append("\n");
		return strb.toString();

	}

	/**
	 * Send the ready code and ready string to the control canal
	 */
	private void writeReady() {
		StringBuilder response = new StringBuilder();
		response.append(this.readyCode);
		response.append(" ");
		response.append(this.readyPhrase);
		SocketUtils.sendMessageWithFlush(this.writer, response.toString());
	}
	
	/**
	 * Get the data socket needed for this command to write on
	 * @return The data Socket
	 * @throws ListException
	 */
	private Socket getDataSocket() throws ListException {
		ServerSocket clientDataCanal = this.client.getDataCanal();
		Socket dataSocket;
		try {
			dataSocket = clientDataCanal.accept();
		} catch (IOException e) {
			throw new ListException();
		}
		return dataSocket;
	}
	
	/**
	 * 
	 * @return The response that will be sent to the data client socket
	 * @throws ListException
	 */
	private String executeList() throws ListException {
		StringBuilder strb = new StringBuilder();
		File[] files = new File(this.client.getCurrentPath().toString()).listFiles();
		for (File file : files) {
			try {
				strb.append(constructFileString(file.toPath()));
			}catch(IOException ioe) {
				throw new ListException();
			}
		}
		return strb.toString();	
	}

}
