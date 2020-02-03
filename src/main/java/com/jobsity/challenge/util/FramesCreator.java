package com.jobsity.challenge.util;

import java.util.List;

import com.jobsity.challenge.exception.BowlingValidationException;
import com.jobsity.challenge.model.Player;
import com.jobsity.challenge.parser.BowlingParsedData;

/**
 * Interface or Frames Creator. The implementation of this interface must create
 * frames from parsed data.
 * 
 * @author alfonsosebaq
 */
public interface FramesCreator {

	List<Player> createPlayersAndFrames(BowlingParsedData source) throws BowlingValidationException;

}
