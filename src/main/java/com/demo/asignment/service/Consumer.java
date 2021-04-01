package com.demo.asignment.service;

import java.util.Map;

import com.demo.asignment.model.PriceData;
import com.demo.asignment.util.CacheManager;

public class Consumer {
	
	public Map<String, Integer> consumes(String productId){
		PriceData data =  CacheManager.getPayload(productId);
		return data.getPayload();
	}

}
