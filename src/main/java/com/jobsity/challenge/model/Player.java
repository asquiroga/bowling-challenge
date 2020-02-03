package com.jobsity.challenge.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a player
 * 
 * @author alfonsosebaq
 *
 */
public class Player {

	private String name;
	private List<Frame> frames = new ArrayList<Frame>();

	public Player(String name, List<Frame> frames) {
		this.name = name;
		this.frames = frames;
	}

	public String getName() {
		return this.name;
	}

	public List<Frame> getFrames() {
		return this.frames;
	}

	public void addFrame(Frame aFrame) {
		this.frames.add(aFrame);
	}

}
