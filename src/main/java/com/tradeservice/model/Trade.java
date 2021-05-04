package com.tradeservice.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Trade {

	@NotEmpty
	private String tradeId;
	@NotNull
	private Integer version;
	@NotEmpty
	private String counterPartyId;
	@NotEmpty
	private String bookId;
	@NotNull
	private Date maturityDate;
	private Date createdDate = new Date();
	private String expired = "N";

	public Trade() {
		
	}
	
	public Trade(String tradeId, Integer version, String counterPartyId, String bookId, Date maturityDate) {
		this.tradeId = tradeId;
		this.version = version;
		this.counterPartyId = counterPartyId;
		this.bookId = bookId;
		this.maturityDate = maturityDate;
	}

	// Getter
	public String getTradeId() {
		return tradeId;
	}

	public int getVersion() {
		return version;
	}

	public String getCounterPartId() {
		return counterPartyId;
	}

	public String getBookId() {
		return bookId;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public Date getCreatedDate() {
		return createdDate ;
	}

	public String getExpired() {
		return expired;
	}

	// Setter
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public void setCounterPartyId(String counterPartyId) {
		this.counterPartyId = counterPartyId;
	}

	public void setBookID(String bookId) {
		this.bookId = bookId;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setExpired(String expired) {
		this.expired = expired;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");

		return "" + tradeId + " | " + version + " | " + counterPartyId + " | " + bookId + " | " + sdformat.format(maturityDate)
				+ " | " + sdformat.format(createdDate) + " | " + expired;
	}
}
