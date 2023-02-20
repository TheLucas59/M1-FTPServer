package com.ftp.commands;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;

import com.util.threads.ClientThread;

public abstract class TestAbstractCommand {

	Command command;
	Path root = Paths.get("/tmp/testFTP/");
	PrintWriter writer;
	ClientThread client;
	
	public abstract Command init();
	
	@BeforeEach
	public void initCommand() throws IOException {
		this.destroy();
		Files.createDirectory(root);
		this.client = new ClientThread(null, null, root, new Object(), "", "");
		this.command = this.init();
	}
	
	private void destroy() throws IOException {
		if(Files.exists(root)) {
			Files.walk(root)
			    .sorted(Comparator.reverseOrder())
			    .map(Path::toFile)
			    .forEach(File::delete);
		}
	}
}
