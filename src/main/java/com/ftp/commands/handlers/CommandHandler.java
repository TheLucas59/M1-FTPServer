package com.ftp.commands.handlers;

import java.io.PrintWriter;

import com.ftp.commands.Cdup;
import com.ftp.commands.Command;
import com.ftp.commands.CommandConstant;
import com.ftp.commands.Cwd;
import com.ftp.commands.Dele;
import com.ftp.commands.List;
import com.ftp.commands.Mkd;
import com.ftp.commands.Pasv;
import com.ftp.commands.Pwd;
import com.ftp.commands.Retr;
import com.ftp.commands.Rmd;
import com.ftp.commands.Rnfr;
import com.ftp.commands.Rnto;
import com.ftp.commands.Stor;
import com.ftp.commands.Type;
import com.ftpserver.exceptions.CommandException;
import com.ftpserver.exceptions.CommandNotFoundException;
import com.ftpserver.exceptions.NoChangeToAnotherUserException;
import com.ftpserver.exceptions.UserAlreadyLoggedInException;
import com.util.threads.ClientThread;

/**
 * Utility class used to parse user input and execute the adequate command.
 * @author Lucas Plé, Aurélien Plancke
 *
 */
public class CommandHandler {
	
	private CommandHandler() {}

	public static void handleCommand(String request, PrintWriter writer, ClientThread client, Object synchronizer) throws CommandException {
		String[] input = new String[2];
		parseInput(request, input);
		String command = input[0].toUpperCase();
		String param = input[1];
		
		Command commandExecutable = null;
		switch(command){
			case CommandConstant.USER :
				throw new NoChangeToAnotherUserException();
			case CommandConstant.PASS :
				throw new UserAlreadyLoggedInException();
			case CommandConstant.PWD :
				commandExecutable = new Pwd(writer, client);
				break;
			case CommandConstant.CWD :
				commandExecutable = new Cwd(writer, client, param);
				break;
			case CommandConstant.PASV :
				commandExecutable = new Pasv(writer, client);
				break;
			case CommandConstant.STOR :
				commandExecutable = new Stor(writer, client, param, synchronizer);
				break;
			case CommandConstant.RETR :
				commandExecutable = new Retr(writer, client, param);
				break;
			case CommandConstant.DELE :
				commandExecutable = new Dele(writer, client, param);
				break;
			case CommandConstant.MKD :
				commandExecutable = new Mkd(writer, client, param, synchronizer);
				break;
			case CommandConstant.RMD :
				commandExecutable = new Rmd(writer, client, param);
				break;
			case CommandConstant.LIST:
				commandExecutable = new List(writer, client);
				break;
			case CommandConstant.TYPE:
				commandExecutable = new Type(writer);
				break;
			case CommandConstant.CDUP:
				commandExecutable = new Cdup(writer, client);
				break;
			case CommandConstant.RNFR:
				commandExecutable = new Rnfr(writer, client, param);
				break;
			case CommandConstant.RNTO:
				commandExecutable = new Rnto(writer, client, param, synchronizer);
				break;
		default:
			throw new CommandNotFoundException();
		}
		commandExecutable.run();
	}
	
	public static void parseInput(String request, String[] input) {
		String[] commandAndParam = request.split(" ");
		input[0] = commandAndParam[0];
		if(commandAndParam.length > 1) {
			input[1] = commandAndParam[1];
		}
	}
}
