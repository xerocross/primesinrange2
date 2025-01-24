package com.adamfgcross.primesinrange2.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.adamfgcross.primesinrange2.dto.PrimesInRangeRequest;
import com.adamfgcross.primesinrange2.exception.InvalidRangeException;

public class PrimesInRangeService {

	private final int MAX_JOB_LENGTH = 200;
	
	public void computePrimesInRange(PrimesInRangeRequest request) {
		
	}
	
	List<PrimesInRangeJob> getJobsFromInterval(PrimesInRangeRequest request) {
		BigInteger requestLength = request.getRangeMax().subtract(request.getRangeMin());
		if (requestLength.compareTo(BigInteger.valueOf(0)) < 0 ) {
			throw new InvalidRangeException("range min must be less than range max");
		}
		BigInteger maxJobLength = BigInteger.valueOf(MAX_JOB_LENGTH);
		BigInteger numFullSubintervals = requestLength.divide(maxJobLength);
		BigInteger remainder = requestLength.mod(maxJobLength);
		
		List<PrimesInRangeJob> jobs = new ArrayList<>();
		BigInteger i;
		for (i = BigInteger.valueOf(0L); i.compareTo(numFullSubintervals) < 0; i = i.add(BigInteger.valueOf(1L))) {
			var job = new PrimesInRangeJob(i, i.add(maxJobLength));
			jobs.add(job);
		}
		jobs.add(new PrimesInRangeJob(i, i.add(remainder)));
		return jobs;
	}
	
}
