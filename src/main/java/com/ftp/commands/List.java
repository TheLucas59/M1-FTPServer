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
import java.nio.file.attribute.PosixFilePermissions;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.util.Locale;

import com.ftpserver.exceptions.CommandException;
import com.ftpserver.exceptions.ListException;
import com.ftpserver.exceptions.PasvNeededException;
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
	private Object synchronizer;

	public List(PrintWriter writer, ClientThread client, Object synchronizer) {
		super(writer);
		this.client = client;
		this.successCode = 226;
		this.successPhrase = "Directory send OK.";
		this.synchronizer = synchronizer;
	}

	@Override
	protected boolean handleRequest() throws CommandException {
		Socket dataSocket = getDataSocket();
		this.writeReady();
		String response = "";
		synchronized(this.synchronizer) {
			response = executeList();
		}
		try {
			PrintWriter dataWriter = SocketUtils.getWritableOutputStream(dataSocket);
			SocketUtils.sendMessageWithFlush(dataWriter, response);
			dataSocket.close();
			this.client.setDataCanal(null);
		} catch (IOException e) {
			throw new ListException();
		}
		return true;
	}

	/**
	 * 
	 * @param path The path to the file to be analyzed
	 * @return A String that represent a unix-like ls result
	 * @throws IOException
	 */
	private String constructFileString(Path path) throws IOException {
		StringBuilder strb = new StringBuilder();
		
		strb.append(Files.isDirectory(path)?"d":"-");
		strb.append(PosixFilePermissions.toString(Files.getPosixFilePermissions(path)));
		strb.append(" ");
		
		strb.append(Files.getAttribute(path, "unix:nlink"));
		strb.append(" ");
		
		strb.append(Files.getOwner(path).getName());
		strb.append(" ");
		
		strb.append(Files.readAttributes(path, PosixFileAttributes.class, LinkOption.NOFOLLOW_LINKS).group());
		strb.append(" ");
		
		strb.append(Files.size(path));
		strb.append(" ");
		
		LocalDateTime modifiedTime = LocalDateTime.ofInstant(Files.getLastModifiedTime(path).toInstant(), ZoneId.systemDefault());
		DateTimeFormatter dtf; 
		if(modifiedTime.getYear() == LocalDateTime.now().getYear()) {
			dtf = DateTimeFormatter.ofPattern("MMM-dd-HH:mm", Locale.ENGLISH);
		}else {
			dtf = DateTimeFormatter.ofPattern("MMM-dd-yyyy", Locale.ENGLISH);
		}
		strb.append(modifiedTime.format(dtf));
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
	 * @throws CommandException If PASV was not used first or if an IO Exception is raised.
	 */
	private Socket getDataSocket() throws CommandException {
		ServerSocket clientDataCanal = this.client.getDataCanal();
		if(clientDataCanal == null) {
			throw new PasvNeededException();
		}
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
