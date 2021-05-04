package com.tradeservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tradeservice.model.Trade;

@Service
public interface TradeService {

	public void insertTrade(Trade requestedTrade);

	public List<Trade> getAllTrades();

	public void updateTradeWithExpiredDates();

}
