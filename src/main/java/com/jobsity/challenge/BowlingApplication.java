package com.jobsity.challenge;

import java.io.File;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.jobsity.challenge.configuration.AppConfiguration;
import com.jobsity.challenge.exception.BowlingValidationException;
import com.jobsity.challenge.model.Player;
import com.jobsity.challenge.parser.BowlingParsedData;
import com.jobsity.challenge.parser.BownlingParser;
import com.jobsity.challenge.parser.exception.BowlingParserException;
import com.jobsity.challenge.util.FramesCreator;
import com.jobsity.challenge.util.prettyPrint.PrettyPrinter;

/**
 * Hello world!
 *
 */
public class BowlingApplication {
	
	public static void main(String[] args) throws BowlingParserException, BowlingValidationException {
		if (args.length != 1) {
			System.out.println("You must have 1 argument (the file input)");
			System.exit(0);
		}
		
		File inputFile = new File(args[0]);
		
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);

        BownlingParser parser = context.getBean(BownlingParser.class);
		BowlingParsedData parsedData = parser.parse(inputFile);
		List<Player> players = context.getBean(FramesCreator.class).createPlayersAndFrames(parsedData);
		context.getBean(PrettyPrinter.class).printResults(players, System.out);
		
		context.close();
	}
	

}
