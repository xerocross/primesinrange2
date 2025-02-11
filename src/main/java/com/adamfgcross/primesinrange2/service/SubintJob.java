package com.adamfgcross.primesinrange2.service;

import java.math.BigInteger;

public class SubintJob {

	private BigInteger rangeMin;
	private BigInteger rangeMax;
	
	public SubintJob(BigInteger rangeMin, BigInteger rangeMax) {
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
