package com.demo.asignment;

import java.util.Map;

import com.demo.asignment.service.Consumer;
import com.demo.asignment.service.Producer;

public class Main {

	public static void main(String[] args) {		
		Producer producer = new Producer();
		producer.produces("src/main/resources/");
		
		Consumer consumer = new Consumer();
		Map<String, Integer> consumes = consumer.consumes("Television");
		System.out.println("Consumed: "+consumes);
		
	}

}
