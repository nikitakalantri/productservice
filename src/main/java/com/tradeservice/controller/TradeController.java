package com.tradeservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tradeservice.model.Trade;
import com.tradeservice.service.TradeService;

@RestController
@RequestMapping("/api/tradeservice")
public class TradeController {

	@Autowired
	private TradeService tradeStoreService;
	
	@PostMapping(value = "/trades")
	public ResponseEntity<Void> insertTrade(@Valid @RequestBody Trade requestedTrade) {
		System.out.println("Request accepted for adding trade - "+ requestedTrade);
		tradeStoreService.insertTrade(requestedTrade);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@GetMapping(value = "/trades")
	public ResponseEntity<List<Trade>> getAllTrades(){
		return new ResponseEntity<List<Trade>>(tradeStoreService.getAllTrades(), HttpStatus.OK);
	}
	
}
