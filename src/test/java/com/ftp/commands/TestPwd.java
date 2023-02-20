package com.ftp.commands;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.ftpserver.exceptions.PassException;

class TestPwd extends TestAbstractCommand{

	@Test
	protected void testPassException() {
		assertThrows(PassException.class, () ->{
			this.command.run();
		});
	}

	@Override
	protected Command init() {
		return new Pass(writer, "expected", "not expected", "expected", "not expected");
	}

}
