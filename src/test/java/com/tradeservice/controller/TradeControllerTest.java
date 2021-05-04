package com.tradeservice.controller;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.lang.reflect.Method;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradeservice.controller.TradeController;
import com.tradeservice.model.Trade;
import com.tradeservice.service.TradeService;

@WebAppConfiguration
@WebMvcTest(TradeController.class)
public class TradeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private TradeService tradeService;
	
	@InjectMocks
    @Autowired
    private TradeController tradeController;
	
	@Autowired
    private WebApplicationContext context;

	@BeforeMethod
	public void setup(final Method method) {
		initMocks(this);
		mockMvc = webAppContextSetup(context)
				.build();
		ReflectionTestUtils.setField(tradeController, "tradeService", tradeService);
	}

	@Test
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
