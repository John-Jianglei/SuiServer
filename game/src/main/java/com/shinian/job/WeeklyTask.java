package com.shinian.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class WeeklyTask extends QuartzJobBean{
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		try {			
			System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
