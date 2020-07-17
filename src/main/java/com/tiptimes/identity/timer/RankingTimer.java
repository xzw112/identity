package com.tiptimes.identity.timer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RankingTimer {
	
	@Scheduled(cron="0 0 3 * * ?")
	public void checkAgreement(){
		System.out.println("定时任务");
	}
}
