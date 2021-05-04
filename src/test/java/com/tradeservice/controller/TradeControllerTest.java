package com.tradeservice.controller;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradeservice.controller.TradeController;
import com.tradeservice.model.Trade;
import com.tradeservice.service.TradeService;

@WebMvcTest(TradeController.class)
public class TradeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TradeService tradeService;
	
	public void shouldCreateTrade() throws Exception {
		Date maturityDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));
		Trade trade = new Trade("T1", 1, "CP-1", "B1", maturityDate);
		doNothing().when(tradeService).insertTrade(trade);
		this.mockMvc.perform(post("/api/tradeservice/trades").content(convertToJsonString(trade)))
				.andExpect(status().isCreated());
	}

	private String convertToJsonString(Trade trade) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(trade);
	}

}
