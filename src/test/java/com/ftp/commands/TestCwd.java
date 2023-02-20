package com.ftp.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.ftpserver.exceptions.CommandException;
import com.ftpserver.exceptions.FailedChangeDirectoryException;

class TestCwd extends TestAbstractCommand{

	
	@Test
	protected void testFailedChangeDirectoryException() {
		assertThrows(FailedChangeDirectoryException.class, () -> {
			this.command.run();
		});
	}

	@Override
	protected Command init() {
		return new Cwd(writer, client, "../");
	}

}
