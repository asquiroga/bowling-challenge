package com.jobsity.challenge.model;

/**
 * 
 * An instance of this object represents a value in the scoring table. This
 * value can be a number (from 0 to 10), or a Fault
 * 
 * @author alfonsosebaq
 *
 */
public class ScoringPoint {

	private int number;
	private boolean fault = false;

	public ScoringPoint(int number) {
		this.number = number;
	}

	public ScoringPoint(boolean fault) {
		this.fault = fault;
		if (fault)
			this.number = 0;
	}

	public int getNumber() {
		return number;
	}

	public boolean isFault() {
		return fault;
	}

	@Override
	public String toString() {
		if (this.fault)
			return "F";
		return String.valueOf(this.number);
	}
	
}
