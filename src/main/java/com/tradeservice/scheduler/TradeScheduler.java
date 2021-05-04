package com.tradeservice.scheduler;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tradeservice.service.TradeService;

@Component
public class TradeScheduler {

	@Autowired
	private TradeService tradeStoreService;
	
	@Scheduled(initialDelay = 1000, fixedRate = 300000)
	public void updateTradeWithExpiredDates() {
		System.out.println("Scheduler triggered at - "+ Instant.now());
		tradeStoreService.updateTradeWithExpiredDates();
	}
}
