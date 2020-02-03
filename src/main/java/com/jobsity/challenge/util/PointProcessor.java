package com.jobsity.challenge.util;

import java.util.ArrayList;
import java.util.List;

import com.jobsity.challenge.exception.BowlingValidationException;
import com.jobsity.challenge.model.Frame;
import com.jobsity.challenge.model.ScoringPoint;

/**
 * 
 * This class process points, and when conditions satisfied, creates frames. It
 * handles internally de frames dependencies
 * 
 * @author alfonsosebaq
 */
public class PointProcessor {

	private List<Frame> framesBuffer = new ArrayList<Frame>();
	private List<ScoringPoint> internalPoints = new ArrayList<ScoringPoint>();
	private List<Frame> framesAwaitingData = new ArrayList<Frame>();

	public PointProcessor() {
	}

	public void process(ScoringPoint newPoint) {
		this.hydrate(newPoint);
		internalPoints.add(newPoint);

		boolean isLast = framesBuffer.size() == 9;
		if (!isLast && (internalPoints.size() == 2 || isStrike())) {
			addFrame(false);
			internalPoints.clear();
		}
	}

	/**
	 * New point arrived. This data is passed to frames that are waiting data to
	 * calculate their score
	 */
	private void hydrate(ScoringPoint newPoint) {
		for (Frame frame : framesAwaitingData) {
			if (!frame.isScoreComputed())
				frame.hydrate(newPoint);
		}
	}

	/**
	 * Flushes the last frame
	 */
	public void flush() throws BowlingValidationException {
		if (internalPoints.size() > 0) {
			addFrame(true);
		}

		validations();
	}

	/**
	 * Multiple validations
	 */
	private void validations() throws BowlingValidationException {
		if (framesBuffer.size() != 10)
			throw new BowlingValidationException("Invalid number of frames: " + framesBuffer.size());
		Frame aFrame = framesAwaitingData.stream().filter(frame -> !frame.isScoreComputed()).findAny().orElse(null);

		if (aFrame != null)
			throw new BowlingValidationException("Not enough data!");

		Frame lastFrame = framesBuffer.get(9);
		int lastFrameSize = lastFrame.getPoints().size();

		if (lastFrameSize > 3)
			throw new BowlingValidationException(
					"Too many points in last frame: " + lastFrame.getPoints().size());
		if (lastFrameSize == 3 && !lastFrame.isStrike() && !lastFrame.isSpare())
			throw new BowlingValidationException(
					"Invalid last frame");
		if (lastFrameSize == 2 && (lastFrame.isStrike() || lastFrame.isSpare()))
			throw new BowlingValidationException(
					"One more point expected in last frame");
	}

	/**
	 * Adds current frame
	 */
	private void addFrame(boolean isLastFrame) {
		Frame newFrame = new Frame(internalPoints);
		framesBuffer.add(newFrame);
		if (!isLastFrame && !newFrame.isScoreComputed())
			framesAwaitingData.add(newFrame);
	}

	private boolean isStrike() {
		return internalPoints.size() == 1 && internalPoints.get(0).getNumber() == 10;
	}

	public List<Frame> getFrames() {
		return this.framesBuffer;
	}

}
