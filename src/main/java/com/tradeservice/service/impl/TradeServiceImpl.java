package com.tradeservice.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.tradeservice.exception.TradeException;
import com.tradeservice.model.Trade;
import com.tradeservice.service.TradeService;

@Component
public class TradeServiceImpl implements TradeService {

	private HashMap<String,List<Trade>> tradeMap = new HashMap<String, List<Trade>>() ;
	
	@Override
	public void insertTrade(Trade requestedTrade) {

		validateTrade(requestedTrade);
		/*
		 * If the version is same it will override the existing record.
		 */
		Trade existingTradeWithSameVersion = findByTradeIdAndVersion(requestedTrade.getTradeId(),
				requestedTrade.getVersion());
		List<Trade> trades = tradeMap.get(requestedTrade.getTradeId());
		if( null == trades) {
			trades = new ArrayList<Trade>();
		}
		trades.remove(existingTradeWithSameVersion);
		trades.add(requestedTrade);
		tradeMap.put(requestedTrade.getTradeId(), trades);
	}

	public Trade findByTradeIdAndVersion(String tradeId, int version) {
		Trade trade = null;
		List<Trade> trades = tradeMap.get(tradeId);
		if (!CollectionUtils.isEmpty(trades)) {
			trade = trades.stream().filter(t -> t.getVersion() == version).findAny().orElse(null);
		}
		return trade;
	}

	@Override
	public List<Trade> getAllTrades() {
		List<Trade> allTrades = new ArrayList<>();
		tradeMap.values().forEach(t -> allTrades.addAll(t));
		return allTrades;
	}
	
	private void validateTrade(Trade requestedTrade) {
		System.out.println("Validating trade - "+ requestedTrade);
		Date currentDate = new Date();
		/*
		 * Check if maturityDate is less than current date.
		 * */
		if (currentDate.compareTo(requestedTrade.getMaturityDate()) > 0) {
			throw new TradeException("Maturity Date is less than current date", HttpStatus.BAD_REQUEST);
		}
		
		/*
		 * Check if the lower version is being received by the store it will reject the
	 	   trade and throw an exception.
		 * */
		if (tradeMap != null) {
			List<Trade> existingTrades = tradeMap.get(requestedTrade.getTradeId());
			if ( !(CollectionUtils.isEmpty(existingTrades)) ) {
				existingTrades.stream().filter(t -> requestedTrade.getVersion() < t.getVersion()).anyMatch(t -> {
					throw new TradeException("The Version in the Current trade is less than existing trade",
							HttpStatus.BAD_REQUEST);
				});
			}
		}
	}

	@Override
	public void updateTradeWithExpiredDates() {
		Date currentDate = new Date();
		for (String strKey : tradeMap.keySet()) {
			List<Trade> updatedTrades = new ArrayList<Trade>();
			tradeMap.get(strKey)
					.forEach(t-> {
						if (currentDate.compareTo(t.getMaturityDate()) > 0) {
							t.setExpired("Y");
						}
						updatedTrades.add(t);
			});
			tradeMap.put(strKey, updatedTrades);
		}
	}
	
}
