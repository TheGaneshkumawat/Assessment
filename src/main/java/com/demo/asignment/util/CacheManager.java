package com.demo.asignment.util;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.demo.asignment.model.PriceData;

public class CacheManager {

	public static final Map<String, PriceData> cache = new ConcurrentHashMap<>();
	
	
    public static void updateCache(List<PriceData> list) {
        if (list.isEmpty()) {
            return;
        }
        
        for(PriceData priceData: list) {
        	if(cache.containsKey(priceData.getId())) {
        		
        		PriceData value = cache.get(priceData.getId());
        		if (value == null) {
                    cache.remove(priceData.getId());
                }
        		if(priceData.getAsof().isAfter(value.getAsof())) {
        			cache.put(priceData.getId(),priceData);
        		}        		
        	}
        	else {
        		cache.put(priceData.getId(),priceData);
        	}
        }
    }
 
    
    public static void remove(String key) {
        cache.remove(key);
    }
 
    
    public static PriceData getPayload(String key) {
        return cache.get(key);
    }    
   
}
