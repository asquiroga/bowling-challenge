package com.jobsity.challenge.util;

import java.util.List;

import com.jobsity.challenge.exception.BowlingValidationException;
import com.jobsity.challenge.model.Frame;
import com.jobsity.challenge.model.ScoringPoint;

/**
 * Simple interface. A point processor just processes ScoringPoints
 * 
 * @author alfonsosebaq
 */
public interface PointProcessor {

	void process(ScoringPoint newPoint);

	void flush() throws BowlingValidationException;

	List<Frame> getFrames();

}
