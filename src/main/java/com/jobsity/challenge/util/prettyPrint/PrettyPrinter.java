package com.jobsity.challenge.util.prettyPrint;

import java.io.PrintStream;
import java.util.List;

import com.jobsity.challenge.model.Player;

/**
 * 
 * This interface implementations should print players data to a stream
 * 
 * @author alfonsosebaq
 */
public interface PrettyPrinter {

	void printResults(List<Player> players, PrintStream out);
	
}
