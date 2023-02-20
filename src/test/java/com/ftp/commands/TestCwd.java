package com.ftp.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.ftpserver.exceptions.FailedChangeDirectoryException;

class TestCwd extends TestAbstractCommand{
	
	@Test
	public void testFailedChangeDirectoryException() {
		assertThrows(FailedChangeDirectoryException.class, () -> {
			this.command.run();
		});
	}

	@Override
	public Command init() {
		return new Cwd(writer, client, "/tmp/testFTP/doesNotExist");
	}

}
