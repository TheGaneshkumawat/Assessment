package com.demo.asignment.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.demo.asignment.model.PriceData;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;

public class Utility {

	public static List<PriceData> parseJson(String fileName) {
		
		System.out.println("Processing the batch....");
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        ImmutableList.Builder<PriceData> builder = ImmutableList.builder();
        
        //file type validation can be done here
        try (FileReader reader = new FileReader("src/main/resources/"+fileName))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray priceDataList = (JSONArray) obj;
            System.out.println(priceDataList);
             
            //Iterate over priceData array
            for(Object object: priceDataList) {
            	//Get priceData object within list
            	JSONObject jsonObj = (JSONObject) object;
            	
                JSONObject priceDataObject = (JSONObject) jsonObj.get("priceData");
                 
                //Get priceData id
                String id = (String) priceDataObject.get("id");
                 
                //Get priceData asof string
                String asof = (String) priceDataObject.get("asof");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");				
				LocalDateTime dateTime = LocalDateTime.parse(asof, formatter);	
                 
                //Get priceData payload
                Map<String, Integer> payloadMap = (Map) priceDataObject.get("payload");    
      
                if(Strings.isNullOrEmpty(id) || dateTime == null || payloadMap.size()<=0) {
                	//corrupted data, do not proceed
                	continue;
                }
                
                builder.add(new PriceData(id,dateTime,payloadMap));
                
            }         
            System.out.println("Batch processed successfully.");
            return builder.build();
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            builder = ImmutableList.builder();
        } catch (IOException e) {
            e.printStackTrace();
            builder = ImmutableList.builder();
        } catch (ParseException e) {
            e.printStackTrace();
            builder = ImmutableList.builder();
        }
        catch (Exception e) {
            e.printStackTrace();
            builder = ImmutableList.builder();
        }
        
        //If any record failed to Parse returning empty list to discard the current batch
		return builder.build();
	}
	
	
	
}
