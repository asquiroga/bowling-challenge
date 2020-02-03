package com.jobsity.challenge.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents a User frame in the bowling score game 1) It can have 2
 * ScoringPoint (number or 'F') 2) Can be a strike 3) Can be a spare
 * 
 * @author alfonsosebaq
 *
 */
public class Frame {

	private List<ScoringPoint> points = new ArrayList<ScoringPoint>();
	private List<ScoringPoint> hydratedPoints = new ArrayList<ScoringPoint>();
	private int score;

	/*
	 * This flag is false when needs data from other frames to calculate the score
	 */
	private boolean scoreComputed = false;

	public Frame(List<ScoringPoint> aPointList) {
		this.points.addAll(aPointList);
		this.setScore(this.getBaseScore());
		if (!this.isSpare() && !this.isStrike())
			this.scoreComputed = true;
	}

	public List<ScoringPoint> getPoints() {
		return this.points;
	}

	public boolean isScoreComputed() {
		return this.scoreComputed;
	}

	/** New data arrived to calculate the current score */
	public void hydrate(ScoringPoint newPoint) {
		hydratedPoints.add(newPoint);
		if (this.isSpare()) {
			this.setScore(this.getScore() + newPoint.getNumber());
			this.scoreComputed = true;
		}

		if (this.isStrike() && hydratedPoints.size() == 2) {
			int valueToSum = hydratedPoints.get(0).getNumber() + hydratedPoints.get(1).getNumber();
			this.setScore(this.getScore() + valueToSum);
			this.scoreComputed = true;
		}
	}

	/**
	 * Calculates the base score (without dependencies)
	 */
	private int getBaseScore() {
		int score = 0;
		for (ScoringPoint scoringPoint : points) {
			score += scoringPoint.getNumber();
		}
		return score;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isStrike() {
		return this.points.get(0).getNumber() == 10;
	}

	public boolean isSpare() {
		return this.points.size() >= 2 && (this.points.get(0).getNumber() + this.points.get(1).getNumber() == 10);
	}

}
