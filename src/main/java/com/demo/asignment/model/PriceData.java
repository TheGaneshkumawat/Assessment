package com.demo.asignment.model;

import java.time.LocalDateTime;
import java.util.Map;

public class PriceData {

	String id;
	LocalDateTime asof;
	
	//flexible DS
	Map<String, Integer> payload;

	public PriceData(String id, LocalDateTime asof, Map<String, Integer> payloadMap) {
		super();
		this.id = id;
		this.asof = asof;
		this.payload = payloadMap;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getAsof() {
		return asof;
	}

	public void setAsof(LocalDateTime asof) {
		this.asof = asof;
	}

	public Map<String, Integer> getPayload() {
		return payload;
	}

	public void setPayload(Map<String, Integer> payload) {
		this.payload = payload;
	}
	
	
	
}
