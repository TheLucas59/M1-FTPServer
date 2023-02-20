package com.ftp.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.ftpserver.exceptions.CreateDirectoryFailedException;

class TestMkd extends TestAbstractCommand {

	String dirToCreate = this.root.toString() + "/anotherTest";

	@Test
	protected void testCreateDirectoryFailedException() throws IOException {
	Files.createDirectory(Paths.get(dirToCreate));
	assertThrows(CreateDirectoryFailedException.class, () ->{
		this.command.run();
	});
}
		

	@Override
	protected Command init() {
		return new Mkd(writer, client, dirToCreate, client);
	}

}
