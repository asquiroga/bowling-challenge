package com.jobsity.challenge;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.jobsity.challenge.configuration.AppConfiguration;
import com.jobsity.challenge.exception.BowlingValidationException;
import com.jobsity.challenge.model.Frame;
import com.jobsity.challenge.model.Player;
import com.jobsity.challenge.model.ScoringPoint;
import com.jobsity.challenge.parser.BowlingParsedData;
import com.jobsity.challenge.util.FramesCreator;

import junit.framework.TestCase;

public class FrameCreatorTest extends TestCase {

	private FramesCreator framesCreator;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        framesCreator = context.getBean(FramesCreator.class);
        context.close();
	}
	
	public void testOnlyStrikesExceptLast() throws BowlingValidationException {
		BowlingParsedData testData = new BowlingParsedData();

		for (int i = 0; i < 9; i++) {
			testData.addPlayerScoringPoint("Mike", new ScoringPoint(10));
		}

		testData.addPlayerScoringPoint("Mike", new ScoringPoint(5));
		testData.addPlayerScoringPoint("Mike", new ScoringPoint(true));

		List<Player> players = framesCreator.createPlayersAndFrames(testData);

		assertEquals(1, players.size());

		List<Frame> frames = players.get(0).getFrames();
		assertEquals(10, frames.size());
	}

	public void testAllStrikes() throws BowlingValidationException {
		BowlingParsedData testData = new BowlingParsedData();

		for (int i = 0; i < 9; i++) {
			testData.addPlayerScoringPoint("Mike", new ScoringPoint(10));
		}

		testData.addPlayerScoringPoint("Mike", new ScoringPoint(10));
		testData.addPlayerScoringPoint("Mike", new ScoringPoint(4));
		testData.addPlayerScoringPoint("Mike", new ScoringPoint(1));

		List<Player> players = framesCreator.createPlayersAndFrames(testData);
		assertEquals(1, players.size());
		List<Frame> frames = players.get(0).getFrames();
		assertEquals(10, frames.size());
		assertEquals(1, frames.get(9).getPoints().get(2).getNumber());
	}

	public void testAllStrikesAndLast() throws BowlingValidationException {
		BowlingParsedData testData = new BowlingParsedData();

		for (int i = 0; i < 9; i++) {
			testData.addPlayerScoringPoint("Mike", new ScoringPoint(10));
		}

		testData.addPlayerScoringPoint("Mike", new ScoringPoint(10));
		testData.addPlayerScoringPoint("Mike", new ScoringPoint(10));
		testData.addPlayerScoringPoint("Mike", new ScoringPoint(10));

		List<Player> players = framesCreator.createPlayersAndFrames(testData);
		assertEquals(1, players.size());
		List<Frame> frames = players.get(0).getFrames();
		assertEquals(10, frames.size());
		assertEquals(10, frames.get(9).getPoints().get(2).getNumber());
	}

	public void testAllStrikesAndSpare() throws BowlingValidationException {
		BowlingParsedData testData = new BowlingParsedData();

		for (int i = 0; i < 9; i++) {
			testData.addPlayerScoringPoint("Mike", new ScoringPoint(10));
		}

		testData.addPlayerScoringPoint("Mike", new ScoringPoint(6));
		testData.addPlayerScoringPoint("Mike", new ScoringPoint(4));
		testData.addPlayerScoringPoint("Mike", new ScoringPoint(1));

		List<Player> players = framesCreator.createPlayersAndFrames(testData);
		assertEquals(1, players.size());
		List<Frame> frames = players.get(0).getFrames();
		assertEquals(10, frames.size());

	}

	public void testAllStrikesAndSpareFail() {
		BowlingParsedData testData = new BowlingParsedData();

		for (int i = 0; i < 9; i++) {
			testData.addPlayerScoringPoint("Mike", new ScoringPoint(10));
		}

		testData.addPlayerScoringPoint("Mike", new ScoringPoint(6));
		testData.addPlayerScoringPoint("Mike", new ScoringPoint(4));
		testData.addPlayerScoringPoint("Mike", new ScoringPoint(1));
		testData.addPlayerScoringPoint("Mike", new ScoringPoint(3)); // This data is unexpected

		try {
			framesCreator.createPlayersAndFrames(testData);
			fail("Should raise Exception!");
		} catch (BowlingValidationException e) {
			assertTrue(e.getMessage().toLowerCase().contains("too many"));
		}

	}

	public void testMultipleFaults() throws BowlingValidationException {
		BowlingParsedData testData = new BowlingParsedData();

		for (int i = 0; i < 20; i++) {
			testData.addPlayerScoringPoint("Mike", new ScoringPoint(true));
		}

		List<Player> players = framesCreator.createPlayersAndFrames(testData);
		assertEquals(1, players.size());
		List<Frame> frames = players.get(0).getFrames();
		assertEquals(10, frames.size());
	}

	public void testNonStrikes() throws BowlingValidationException {
		BowlingParsedData testData = new BowlingParsedData();
		testData.addPlayerScoringPoint("Mike", new ScoringPoint(6));
		testData.addPlayerScoringPoint("Mike", new ScoringPoint(2));
		testData.addPlayerScoringPoint("Mike", new ScoringPoint(9));
		testData.addPlayerScoringPoint("Mike", new ScoringPoint(true));

		for (int i = 0; i < 16; i++) {
			testData.addPlayerScoringPoint("Mike", new ScoringPoint(4));
		}

		List<Player> players = framesCreator.createPlayersAndFrames(testData);
		assertEquals(1, players.size());
		List<Frame> frames = players.get(0).getFrames();
		assertEquals(10, frames.size());
	}

	public void testLastSpareButOnly2data() throws BowlingValidationException {
		BowlingParsedData testData = new BowlingParsedData();

		for (int i = 0; i < 18; i++) {
			testData.addPlayerScoringPoint("Mike", new ScoringPoint(4));
		}

		testData.addPlayerScoringPoint("Mike", new ScoringPoint(9));
		testData.addPlayerScoringPoint("Mike", new ScoringPoint(1));

		try {
			framesCreator.createPlayersAndFrames(testData);
			fail("Should raise Exception!");
		} catch (BowlingValidationException e) {
			assertTrue(e.getMessage().toLowerCase().contains("one more"));
		}
	}

}
