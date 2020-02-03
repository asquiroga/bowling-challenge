package com.jobsity.challenge.parser;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.jobsity.challenge.model.ScoringPoint;
import com.jobsity.challenge.parser.exception.BowlingParserException;

/**
 * This implementation reads a file and expects data as this: 
 * [aName] [tab char] [a value]
 * 
 * where the value must be a number from 0 to 10, o a 'F' character
 * 
 * If the data does not have the expected structure, an expection is raised
 * 
 * @author alfonsosebaq
 *
 */
public class BowlingParserImpl implements BownlingParser {

	public BowlingParsedData parse(File aFile) throws BowlingParserException {
		if (!aFile.exists())
			throw new BowlingParserException("File does not exist!");
		if (aFile.length() == 0)
			throw new BowlingParserException("Empty file");

		BowlingParsedData result = new BowlingParsedData();
		List<String> fileLines;
		try {
			fileLines = FileUtils.readLines(aFile, "UTF8");
		} catch (IOException e) {
			throw new BowlingParserException("Exception while reading the input file", e);
		}

		for (String string : fileLines) {
			String[] lineItems = string.split("\\t");
			if (lineItems.length != 2) {
				throw new BowlingParserException("Unexpected words per line: " + string);
			}

			String name = lineItems[0].trim();
			ScoringPoint currentScoringPoint;
			String scoringValue = lineItems[1].trim();
			if (scoringValue.length() == 1 && scoringValue.toUpperCase().equals("F")) {
				currentScoringPoint = new ScoringPoint(true);
			} else if (isValidNumber(scoringValue)) {
				currentScoringPoint = new ScoringPoint(Integer.parseInt(scoringValue));
			} else {
				throw new BowlingParserException("Unexpected scoring value: " + scoringValue);
			}

			result.addPlayerScoringPoint(name, currentScoringPoint);
		}

		return result;
	}

	private static boolean isValidNumber(String aString) {
		if (aString == null) {
			return false;
		}
		int number;
		try {
			number = Integer.parseInt(aString);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return number >= 0 && number <= 10;
	}

}
