package com.ftp.commands;

import java.io.PrintWriter;
import java.nio.file.Path;

import com.ftpserver.exceptions.CommandException;

public class Pwd extends Command {
	
	private Path currentPath;
	private Path rootPath;
	
	public Pwd(PrintWriter writer, Path currentPath, Path rootPath) {
		super(writer);
		this.successCode = 257;
		this.currentPath = currentPath;
		this.rootPath = rootPath;
	}

	@Override
	public boolean handleRequest() throws CommandException {
		String pathString = this.currentPath.toString();
		System.out.println(pathString);
		String rootString = this.rootPath.toString();
		pathString = pathString.replace(rootString, "/");
		System.out.println(pathString);
		this.successPhrase = "\"" + pathString + "\" is the current directory";
		writeSuccess();
		return true;
	}

}
