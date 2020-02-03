package com.jobsity.challenge.util.prettyPrint;

import java.io.PrintStream;
import java.util.List;

import com.jobsity.challenge.model.Frame;
import com.jobsity.challenge.model.Player;
import com.jobsity.challenge.model.ScoringPoint;

public class PrettyPrinterImpl implements PrettyPrinter {

	private static final int DATA_WIDTH = 7;
	
	public void printResults(List<Player> players, PrintStream out) {
		out.print("\n\nFrame     ");
		for (int i = 1; i <= 10; i++) {
			out.print(pad(i, DATA_WIDTH) + "| ");
		}

		for (Player player : players) {
			prettyPrintPlayer(player, out);
		}
	}

	private void prettyPrintPlayer(Player player, PrintStream out) {
		out.println("\n\n" + player.getName());

		out.print("Pinfalls  ");
		for (Frame aFrame : player.getFrames()) {
			out.print(pad(prettyFormat(aFrame), DATA_WIDTH) + "| ");
		}

		out.print("\nScore     ");
		int currentScore = 0;
		for (Frame aFrame : player.getFrames()) {
			currentScore += aFrame.getScore();
			out.print(pad(currentScore, DATA_WIDTH) + "| ");
		}
	}
	
	private static String prettyFormat(Frame aFrame) {
		if (aFrame.isSpare())
			return aFrame.getPoints().get(0) + " /";
		if (aFrame.isStrike() && aFrame.getPoints().size() < 3)
			return "  X";
		String text = "";
		for (ScoringPoint aPoint : aFrame.getPoints()) {
			text += aPoint + " ";
		}
		return text;
	}

	private static String pad(Integer number, int size) {
		return pad(String.valueOf(number), size);
	}

	private static String pad(String aString, int size) {
		return String.format("%1$-" + size + "s", aString);
	}

}
