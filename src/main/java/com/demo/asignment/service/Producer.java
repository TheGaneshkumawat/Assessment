package com.demo.asignment.service;

import java.io.File;
import java.util.List;

import com.demo.asignment.model.PriceData;
import com.demo.asignment.util.CacheManager;
import com.demo.asignment.util.Utility;

public class Producer {
	
	public String produces(String directoryPath) {
		
		
		File folder = new File(directoryPath);
		File[] listOfFiles = folder.listFiles();
						
		for (int i = 0; i < listOfFiles.length; i++) {			
			//It is assumed that the Folder contains only files	
			List<PriceData> list = Utility.parseJson(listOfFiles[i].getName());			
									
			CacheManager.updateCache(list);
		}	
		
		return "Completed";
	}

}
