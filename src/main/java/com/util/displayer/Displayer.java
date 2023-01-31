package com.util.displayer;

import com.ftp.commands.Command;

/**
 * Wrapper for displaying messages
 * @author Aurélien Plancke
 *
 */
public class Displayer {

	private static Displayer displayer;
	
	private Displayer() {}
	
	public Displayer getInstance() {
		if(displayer == null) {
			return new Displayer();
		}
		return displayer;
	}
}