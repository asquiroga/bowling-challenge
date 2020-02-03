package com.jobsity.challenge;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.jobsity.challenge.configuration.AppConfiguration;
import com.jobsity.challenge.exception.BowlingValidationException;
import com.jobsity.challenge.model.ScoringPoint;
import com.jobsity.challenge.parser.BowlingParsedData;
import com.jobsity.challenge.parser.BownlingParser;
import com.jobsity.challenge.parser.exception.BowlingParserException;

import junit.framework.TestCase;

public class ParserTest extends TestCase {

	private BownlingParser bowlingParser;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		bowlingParser = context.getBean(BownlingParser.class);
		context.close();
	}

	public void testPlayerNames() throws BowlingParserException {
		BowlingParsedData data = parseTestData();

		List<String> playerNames = data.getPlayerNames();
		assertEquals("Dylan", playerNames.get(0));
		assertEquals("Joseph", playerNames.get(1));
	}

	public void testgetInexistentPlayer() throws BowlingParserException {
		BowlingParsedData data = parseTestData();
		
		List<ScoringPoint> points = data.getPlayerPoints("Richard");
		assertNull(points);
	}

	public void testFirstPlayerData() throws BowlingParserException {
		BowlingParsedData data = parseTestData();
		
		List<ScoringPoint> points = data.getPlayerPoints("Dylan");
		assertEquals(14, points.size());
		assertEquals(9, points.get(4).getNumber());
		assertEquals(false, points.get(4).isFault());
		assertEquals(true, points.get(9).isFault());
	}

	public void testSecondPlayerData() throws BowlingParserException {
		BowlingParsedData data = parseTestData();
		
		List<ScoringPoint> points = data.getPlayerPoints("Joseph");
		assertEquals(12, points.size());
		assertEquals(10, points.get(7).getNumber());
		assertEquals(false, points.get(7).isFault());
	}

	private BowlingParsedData parseTestData() throws BowlingParserException {
		File input = getFile("inputTest.txt");
		return bowlingParser.parse(input);
	}

	private File getFile(String aFileName) {
		try {
			return new File(ParserTest.class.getClassLoader().getResource(aFileName).toURI());
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}

	}

}
