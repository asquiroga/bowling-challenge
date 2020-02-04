package com.jobsity.challenge.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jobsity.challenge.exception.BowlingValidationException;
import com.jobsity.challenge.model.Frame;
import com.jobsity.challenge.model.Player;
import com.jobsity.challenge.model.ScoringPoint;
import com.jobsity.challenge.parser.BowlingParsedData;
import com.jobsity.challenge.spring.Context;


/**
 * This class has the logic to create frames from the parsed data
 * 
 * @author alfonsosebaq
 */
public class FramesCreatorImpl implements FramesCreator {
	
	public List<Player> createPlayersAndFrames(BowlingParsedData source) throws BowlingValidationException {
		ArrayList<Player> result = new ArrayList<Player>();

		for (String aPlayerName : source.getPlayerNames()) {
			List<ScoringPoint> points = source.getPlayerPoints(aPlayerName);
			List<Frame> playerFrames = reducePointsToFrames(points);
			result.add(new Player(aPlayerName, playerFrames));
		}

		return result;
	}

	private List<Frame> reducePointsToFrames(List<ScoringPoint> points) throws BowlingValidationException {
		PointProcessor processor = Context.getContext().getBean(PointProcessor.class);
		for (ScoringPoint scoringPoint : points) {
			processor.process(scoringPoint);
		}
		processor.flush();

		return processor.getFrames();
	}
	


}
