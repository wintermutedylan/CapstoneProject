package com.capstone.cars;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.FindIterable;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;



import org.bson.Document;

public class DBInteract {
	
	private static MongoClient mClient;
	private static ArrayList<String> attributeNames = new ArrayList<String>();
	
	private static void populateAttributeNames() {
		
    	attributeNames.add("oil change");
    	attributeNames.add("transmission fluid change");
    	attributeNames.add("antifreeze (coolant)");
    	attributeNames.add("tire rotation");
    	attributeNames.add("tire replacement");
    	attributeNames.add("engine airfilter");
    	attributeNames.add("cabin airfilter");
    	attributeNames.add("windshield wiper");
    	attributeNames.add("rear wiper");
    	attributeNames.add("break fluid change");
    	attributeNames.add("spark plugs");
    	attributeNames.add("power steering fluid change");
    	attributeNames.add("battery");
    	attributeNames.add("timing belt");
    	attributeNames.add("safety inspection");
    	attributeNames.add("emmissions inspection");
    	attributeNames.add("car wash");
		
	}
	
	private static MongoClient getMongoClient() {
		if (mClient == null) {
			mClient = new MongoClient("localhost", 27017);
		}
		return mClient;
	}
	
	private static MongoDatabase getDB() {
		return getMongoClient().getDatabase("myMongoDb");
	}
	
	public static MongoCollection<Document> getCol(){
		
		return getDB().getCollection("CarsTest");
	}
	
	public static List<Car> readDocsFromCollection() {
		List<Car> docList = new ArrayList<Car>();
		FindIterable<Document> myCursor = getCol().find();
		
		
		myCursor.cursor().forEachRemaining(t -> docList.add(CarConverter.toCar(t)));
		
		
			
		
		return docList;
	}
	
	public static void storeInDB(Document doc) {
		
		getCol().insertOne(doc);
		
	}
	public static void deleteDocInDB(Bson doc) {
		getCol().deleteOne(doc);
		
	}
	
	
	
	
	
	public static Document addAttribute(String mile) {
		if (attributeNames.isEmpty()) {
			populateAttributeNames();
		}
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		Document results = new Document();
		for (String x : attributeNames) {
			results.append(x, new Document()
					.append("mileage", mile)
					.append("last_update", date.format(formatter)));
		}
		return results;
	}
	
	

}
