package com.ftp.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.ftpserver.exceptions.RemoveDirectoryFailedException;

class TestRmd extends TestAbstractCommand{

	@Test
	public void testRemoveDirectoryFailedException() throws IOException {
		assertThrows(RemoveDirectoryFailedException.class, () -> {
			this.command.run();
		});
	}

	@Override
	public Command init() {
		Path pathTest = Paths.get(this.root.toString() + "/testFolder");
		return new Rmd(writer, client, pathTest.toString(), pathTest);
	}

}
