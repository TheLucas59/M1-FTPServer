package com.ftp.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.ftpserver.exceptions.FileDoesNotExistException;

class TestDele extends TestAbstractCommand{

	@Test
	public void testFileDoesNotExistException() throws IOException {
		assertThrows(FileDoesNotExistException.class, () -> {
			this.command.run();
		});
	}

	@Override
	public Command init() {
		Path pathTest = Paths.get(this.root.toString() + "/testFile");
		return new Dele(writer, client, pathTest.toString(), pathTest);
	}

}
