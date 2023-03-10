package com.ftp.commands;

/**
 * Constant string of the expected commands name
 * @author Aurélien Plancke
 *
 */
public class CommandConstant {
	public static final String USER = "USER";
	public static final String PASS = "PASS";
	public static final String PWD = "PWD";
	public static final String CWD = "CWD";
	public static final String PASV = "PASV";
	public static final String LIST = "LIST";
	public static final String DELE = "DELE";
	public static final String STOR = "STOR";
	public static final String RETR = "RETR";
	public static final String MKD = "MKD";
	public static final String RMD = "RMD";
	public static final String TYPE = "TYPE";
	public static final String CDUP = "CDUP";
	public static final String RNFR = "RNFR";
	public static final String RNTO = "RNTO";
	public static final String QUIT = "QUIT";
	
	private CommandConstant() {}
}
