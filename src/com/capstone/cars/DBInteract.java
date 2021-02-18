package com.capstone.cars;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.FindIterable;

import java.util.List;
import java.util.ArrayList;

import org.bson.Document;

public class DBInteract {
	
	private static MongoClient mClient;
	
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
			
		//}
			
		
		return docList;
	}
	
	public static void storeInDB(Document doc) {
		getCol().insertOne(doc);
		
	}
	
	

}
