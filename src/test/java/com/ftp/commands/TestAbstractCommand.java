package com.ftp.commands;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import com.util.threads.ClientThread;

abstract class TestAbstractCommand {

	Command command;
	Path root = Paths.get("/home/aurelien/tmp");
	PrintWriter writer;
	ClientThread client;
	
	abstract protected Command init();
	
	@BeforeEach
	protected void initCommand() throws IOException {
		Files.createDirectory(root);
		this.client = new ClientThread(null, null, root, root, "", "");
		this.command = this.init();
	}
	
	@AfterEach
	protected void destroy() throws IOException {
		Files.delete(root);
	}
}
