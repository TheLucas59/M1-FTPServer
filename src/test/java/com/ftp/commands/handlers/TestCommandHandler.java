package com.ftp.commands.handlers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.ftpserver.exceptions.CommandException;
import com.ftpserver.exceptions.CommandNotFoundException;

class TestCommandHandler {

	@Test
	protected void testExceptionThrown() throws CommandException {
		assertThrows(CommandNotFoundException.class, () -> {
			CommandHandler.handleCommand("test", null, null);			
		});
	}

}
