package com.ftp.commands;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.ftpserver.exceptions.PassException;

class TestPass extends TestAbstractCommand{

	@Test
	public void testPassException() {
		assertThrows(PassException.class, () ->{
			this.command.run();
		});
	}

	@Override
	public Command init() {
		return new Pass(writer, "expectedUser", "notExpectedUser", "expectedPassword", "notExpectedPassword");
	}

}
