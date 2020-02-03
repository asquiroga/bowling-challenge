package com.jobsity.challenge.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jobsity.challenge.model.ScoringPoint;

/**
 * This is the raw structure of the data parsed from a file.
 * It must be able to add ScoringPoints to a player.
 * 
 * @author alfonsosebaq
 *
 */
public class BowlingParsedData {

	/** Every player has a list of scoringPoints */
	private Map<String, List<ScoringPoint>> internalData = new HashMap<String, List<ScoringPoint>>();

	public BowlingParsedData() {
	}

	public void addPlayerScoringPoint(String aPlayerName, ScoringPoint point) {
		if (this.internalData.containsKey(aPlayerName)) {
			this.internalData.get(aPlayerName).add(point);
		} else {
			ArrayList<ScoringPoint> newList = new ArrayList<ScoringPoint>();
			newList.add(point);
			this.internalData.put(aPlayerName, newList);
		}
	}

	public List<String> getPlayerNames() {
		return new ArrayList<String>(this.internalData.keySet());
	}

	public List<ScoringPoint> getPlayerPoints(String aPlayerName) {
		return this.internalData.get(aPlayerName);
	}

}
