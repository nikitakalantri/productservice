package com.tradeservice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.tradeservice.exception.TradeException;
import com.tradeservice.model.Trade;
import com.tradeservice.service.impl.TradeServiceImpl;

public class TradeServiceTest {

	TradeService classToTest;

	SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");

	@Before
	public void initialize() {
		classToTest = new TradeServiceImpl();
	}

	@Test
	public void testGetAllTrades() throws Exception {
		Date maturityDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));
		Trade t1 = new Trade("T1", 1, "CP-1", "B1", maturityDate);
		Trade t2 = new Trade("T2", 2, "CP-2", "B1", maturityDate);
		classToTest.insertTrade(t1);
		classToTest.insertTrade(t2);
		assertEquals(2, classToTest.getAllTrades().size());
	}

	@Test
	public void testInvalidMaturityDate() throws Exception {
		Date maturityDate = Date.from(Instant.now().minus(1, ChronoUnit.DAYS));
		Trade t1 = new Trade("T3", 1, "CP-3", "B1", maturityDate);
		assertThrows(TradeException.class, () -> {
			classToTest.insertTrade(t1);
		});
	}
	
	@Test
	public void testInvalidVersion() throws Exception {
		Date maturityDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));
		Trade t1 = new Trade("T4", 2, "CP-4", "B1", maturityDate);
		Trade t2 = new Trade("T4", 1, "CP-1", "B1", maturityDate);
		classToTest.insertTrade(t1);
		assertThrows(TradeException.class, () -> {
			classToTest.insertTrade(t2);
		});
	}
	
	@Test
	public void testSameVersion() throws Exception {
		Date maturityDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));
		Trade t1 = new Trade("T5", 2, "CP-4", "B1", maturityDate);
		Trade t2 = new Trade("T5", 2, "CP-1", "B1", maturityDate);
		classToTest.insertTrade(t1);
		classToTest.insertTrade(t2);
		List<Trade> allTrades = classToTest.getAllTrades();
		assertEquals(1, allTrades.size());
		assertEquals(t2.getCounterPartId(), allTrades.get(0).getCounterPartId());
	}
	
	@Test
	public void testHigherVersion() throws Exception {
		Date maturityDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));
		Trade t1 = new Trade("T6", 2, "CP-4", "B1", maturityDate);
		Trade t2 = new Trade("T6", 4, "CP-6", "B1", maturityDate);
		classToTest.insertTrade(t1);
		classToTest.insertTrade(t2);
		List<Trade> allTrades = classToTest.getAllTrades();
		assertEquals(2, allTrades.size());
	}
	
	@Test
	public void testExpiry() throws Exception {
		Date maturityDate = Date.from(Instant.now().plus(5, ChronoUnit.SECONDS));
		Trade t1 = new Trade("T6", 2, "CP-4", "B1", maturityDate);
		classToTest.insertTrade(t1);
		Thread.sleep(7000);
		classToTest.updateTradeWithExpiredDates();
		assertEquals("Y", classToTest.getAllTrades().get(0).getExpired());
	}
}
