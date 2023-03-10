package com.ftp.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.ftpserver.exceptions.UnautorizedException;

public class TestCdup extends TestAbstractCommand {

	@Test
	public void testUnautorizedException() {
		assertThrows(UnautorizedException.class, () -> {
			this.command.run();
		});
	}
	
	
	@Override
	public Command init() {
		return new Cdup(null, client);
	}

}
