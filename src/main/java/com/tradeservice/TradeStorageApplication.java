package com.tradeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TradeStorageApplication {

	public static void main(String[] args) {
		System.out.println("Trade Store");
		SpringApplication.run(TradeStorageApplication.class, args);
	}
}