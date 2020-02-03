package com.jobsity.challenge.parser;

import java.io.File;

import com.jobsity.challenge.parser.exception.BowlingParserException;

/**
 * This is the parser interface,
 *  
 * @author alfonsosebaq
 */
public interface BownlingParser {

	BowlingParsedData parse(File aFile) throws BowlingParserException;

}
