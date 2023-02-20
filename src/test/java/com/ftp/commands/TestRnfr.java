package com.ftp.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.ftpserver.exceptions.RnfrFailedException;

public class TestRnfr extends TestAbstractCommand {
	
	@Test
	public void testRnfrFailedException() throws IOException {
		assertThrows(RnfrFailedException.class, () -> {
			this.command.run();
		});
	}

	@Override
	public Command init() {
		Path pathTest = Paths.get(this.root.toString() + "/testFile");
		return new Rnfr(writer, client, pathTest.toString());
	}

}
