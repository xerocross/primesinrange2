package com.adamfgcross.primesinrange2.service;

import java.math.BigInteger;

public class PrimesInRangeJob {

	private BigInteger rangeMin;
	private BigInteger rangeMax;
	
	public PrimesInRangeJob(BigInteger rangeMin, BigInteger rangeMax) {
		super();
		this.rangeMin = rangeMin;
		this.rangeMax = rangeMax;
	}
	
	public BigInteger getRangeMin() {
		return rangeMin;
	}
	public BigInteger getRangeMax() {
		return rangeMax;
	}
}
