package com.adamfgcross.primesinrange2.domain;

import java.math.BigInteger;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class PrimesInRangeJob {

	private BigInteger rangeMin;
	private BigInteger rangeMax;
	
	private BigInteger progressLocation;
	
	private Set<BigInteger> primesInRange = ConcurrentHashMap.newKeySet();

	public BigInteger getProgressLocation() {
		return progressLocation;
	}

	public void setProgressLocation(BigInteger progressLocation) {
		this.progressLocation = progressLocation;
	}

	public BigInteger getRangeMin() {
		return rangeMin;
	}

	public BigInteger getRangeMax() {
		return rangeMax;
	}

	public Set<BigInteger> getPrimesInRange() {
		return primesInRange;
	}
	
	public void allComputationsScheduled() {
		
	}
	
	
}
