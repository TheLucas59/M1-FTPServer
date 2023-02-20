package com.ftp.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.ftpserver.exceptions.FileDoesNotExistException;

class TestDele extends TestAbstractCommand{

	
	Path pathTest;
	@Test
	protected void testFileDoestNotExistException() throws IOException {
		assertThrows(FileDoesNotExistException.class, () -> {
			this.command.run();
		});
	}

	@Override
	protected Command init() {
		pathTest = Paths.get(this.root.toString() + "/test");
		try {
			Files.createFile(pathTest);
		} catch (IOException e) {
		}
		return new Dele(writer, client, pathTest.toString(), pathTest);
	}

}
