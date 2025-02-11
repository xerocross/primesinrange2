package com.adamfgcross.primesinrange2.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.adamfgcross.primesinrange2.domain.PrimesInRangeJob;
import com.adamfgcross.primesinrange2.dto.PrimesInRangeRequest;
import com.adamfgcross.primesinrange2.exception.InvalidRangeException;

@Service
public class PrimesInRangeService {

	@Value("${spring.application.MAX_JOB_LENGTH}")
	private int MAX_JOB_LENGTH;
	
	@Value("${spring.application.NUM_THREADS}")
	private int NUM_THREADS;
	
	@Value("${spring.application.MAX_JOBS}")
	private int MAX_JOBS;
	
	private ExecutorService executorService;
	
	private BlockingQueue<SubintJob> jobQueue = new ArrayBlockingQueue<>(MAX_JOBS);
	
	public PrimesInRangeService() {
		executorService = Executors.newFixedThreadPool(NUM_THREADS);
		JobEnqueuerThread jobEnqueuerThread = new JobEnqueuerThread();
		jobEnqueuerThread.start();
	}
	
	public void computePrimesInRange(PrimesInRangeRequest request) {
		
		var jobs = getJobsFromInterval(request);
		
		
	}
	
	private void scheduleJobs(List<SubintJob> jobs) {
		
	}
	
	private boolean isPrime(BigInteger i) {
		return true;
	}
	
	List<SubintJob> getJobsFromInterval(PrimesInRangeRequest request) {
		BigInteger requestLength = request.getRangeMax().subtract(request.getRangeMin());
		if (requestLength.compareTo(BigInteger.valueOf(0)) < 0 ) {
			throw new InvalidRangeException("range min must be less than range max");
		}
		BigInteger maxJobLength = BigInteger.valueOf(MAX_JOB_LENGTH);
		BigInteger numFullSubintervals = requestLength.divide(maxJobLength);
		BigInteger remainder = requestLength.mod(maxJobLength);
		
		List<SubintJob> jobs = new ArrayList<>();
		BigInteger i;
		for (i = BigInteger.valueOf(0L); i.compareTo(numFullSubintervals) < 0; i = i.add(BigInteger.valueOf(1L))) {
			var job = new SubintJob(i, i.add(maxJobLength));
			jobs.add(job);
		}
		jobs.add(new SubintJob(i, i.add(remainder)));
		return jobs;
	}
	
	
	/*
	 * The purpose of the JobEnqueuerThread is to break up the
	 * overall computation into smaller jobs on subintervals (SubintJob)
	 * which are then pushed onto the JobQueue, with the important
	 * qualification that there is a maximum number of subinterval
	 * jobs to be scheduled at any given time. The purpose of this
	 * is to limit the number of objects created in memory. A
	 * user could potentially submit a very long interval for
	 * computation, which in turn could cause a naive implementation
	 * to schedule so many objects as to cause an out-of-memory
	 * error. Here we avoid that.
	 */
	class JobEnqueuerThread extends Thread {
		
		private LinkedList<PrimesInRangeJob> primesInRangeJobsQueue = new LinkedList<>();
		
		private Boolean isTerminated = false;
		
		private PrimesInRangeJob jobInProgress;
		
		public void PushNewPrimesInRangeJob(PrimesInRangeJob primesInRangeJob) {
			primesInRangeJobsQueue.offer(primesInRangeJob);
		}

		public void run() {
			while (!isTerminated) {
				while (jobInProgress == null) {
					jobInProgress = primesInRangeJobsQueue.poll();
				}
				
				var progress = jobInProgress.getProgressLocation();
				var rangeMax = jobInProgress.getRangeMax();
				
				if (progress.compareTo(rangeMax) < 0) {
					var subIntMax = progress.add(BigInteger.valueOf(MAX_JOB_LENGTH)).min(rangeMax);
					var job = new SubintJob(progress, subIntMax);
					try {
						jobQueue.put(job);
						jobInProgress.setProgressLocation(subIntMax);
						
					} catch (InterruptedException e) {
						continue;
					}
				} else {
					jobInProgress.allComputationsScheduled();
					jobInProgress = null;
				}
			}
		}
	}
	
}
