package com.adamfgcross.primesinrange2.dto;

import java.math.BigInteger;

public class PrimesInRangeRequest {

	private BigInteger rangeMin;
	private BigInteger rangeMax;
	
	public PrimesInRangeRequest (BigInteger rangeMin, BigInteger rangeMax) {
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
