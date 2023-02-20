package com.ftp.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.ftpserver.exceptions.RnfrRequiredFirstException;
import com.ftpserver.exceptions.RntoFailedException;

public class TestRnto extends TestAbstractCommand {

	Path pathTest = Paths.get(this.root.toString() + "/testFolder");

	@Test
	public void testRntoFailedException() throws IOException {
		this.client.setPathToRename(pathTest);
		assertThrows(RntoFailedException.class, () -> {
			this.command.run();
		});	
	}
	
	@Test
	public void testRnfrRequiredFirstException() throws IOException {
		assertThrows(RnfrRequiredFirstException.class, () -> {
			this.command.run();
		});	
	}

	@Override
	public Command init() {
		return new Rnto(writer, client, pathTest.toString(), new Object());
	}
}
