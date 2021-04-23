package com.capstone.cars;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.FindIterable;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;


public class DBInteract {
	
	private static MongoClient mClient;

	private static ArrayList<String> attributeNames = new ArrayList<String>();
	private static ArrayList<String> repairNames = new ArrayList<String>();
	
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
	private static void populateRepairNames() {
		
    	repairNames.add("brake pads - front");
    	repairNames.add("brake pads - rear");
    	repairNames.add("brake rotors - front");
    	repairNames.add("brake rotors - rear");
    	repairNames.add("brake calipers - front");
    	repairNames.add("brake calipers - rear");
    	repairNames.add("shock absorber/strut - front");
    	repairNames.add("shock absorber/strut - rear");
    	repairNames.add("ball joints - front");
    	repairNames.add("ball joints - rear");
    	repairNames.add("tie rods - front");
    	repairNames.add("tie rods - rear");
    	repairNames.add("head lights");
    	repairNames.add("turn signals");
    	repairNames.add("tail lights");
    	repairNames.add("brake lights");
    	repairNames.add("interior lights");
    	repairNames.add("windshield replacement");
    	repairNames.add("side glass replacement");
    	repairNames.add("rear glass replacement");
    	repairNames.add("brake shoes");
    	repairNames.add("serpentine belt");
    	repairNames.add("termostat");
    	repairNames.add("water pump");
    	repairNames.add("oxygen sensors");
    	repairNames.add("muffler");
    	repairNames.add("catalytic converter");
    	repairNames.add("tail pipe");
    	repairNames.add("transmission repair");
    	
		
	}
	
	private static MongoClient getMongoClient() {
		MongoClientURI uri = new MongoClientURI(
    		    "mongodb+srv://dwintermute:CapstoneProject@cluster0.vyhjd.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
			mClient = new MongoClient(uri);
		
		return mClient;
	}
	
	private static MongoDatabase getDB() {
		return getMongoClient().getDatabase("CarsDB");
	}
	
	public static MongoCollection<Document> getCol(){
		
		return getDB().getCollection("Cars");
	}
	
	public static List<Car> readDocsFromCollection(String email) {
		List<Car> docList = new ArrayList<Car>();
		
		
		Bson filter = eq("id", email);
		
		FindIterable<Document> myCursor = getCol().find().filter(filter);;
		
		
		myCursor.cursor().forEachRemaining(t -> docList.add(CarConverter.toCar(t)));
		
		
			
		
		return docList;
	}
	
	public static void storeInDB(Document doc) {
		
		getCol().insertOne(doc);
		
	}
	public static void deleteDocInDB(Bson doc) {
		getCol().deleteOne(doc);
		
	}
	
	
	
	
	/**
	 * Adds an attribute the document
	 * @return the updated document
	 */
	public static Document addAttribute() {
		if (attributeNames.isEmpty()) {
			populateAttributeNames();
		}
		Document results = new Document();
		for (String x : attributeNames) {
			results.append(x, new Document()
					.append("mileage", "0") // zero out mileage for attribute
					.append("last_update", "2000-01-01"));
		}
		return results;
	}
	/**
	 * adds repairs to the docuemnt
	 * @return the new document
	 */
	public static Document addRepairs() {
		if (repairNames.isEmpty()) {
			populateRepairNames();
		}

		Document results = new Document();
		for (String x : repairNames) {
			results.append(x, new Document()
					.append("mileage", "0") // zero out mileage for attribute
					.append("last_update", "2000-01-01"));
		}
		return results;
	}
	
	/**
	 * reads the cars attributes from the document
	 * @param carID the Car ID
	 * @return the list of all the attributes
	 */
	public static List<Attribute> readAttributesFromDocument(String carID){
		if (attributeNames.isEmpty()) {
			populateAttributeNames();
		}
		
		ObjectId o = new ObjectId(carID);
		Bson filter = eq("_id", o);
		FindIterable<Document> myC = getCol().find().filter(filter);
		
		Document document = myC.cursor().next();
		
		List<Attribute> aList = new ArrayList<Attribute>();
		for(String x : attributeNames) {
			String m = (String) document.get("attributes", Document.class).get(x, Document.class).get("mileage");
			String l = (String) document.get("attributes", Document.class).get(x, Document.class).get("last_update");
			Attribute att = new Attribute(x, m, l);
			aList.add(att);
		}
		
		return aList;
		
		
	}
	/**
	 * reads the cars repairs from the document
	 * @param carID the Car ID
	 * @return the list of all the repairs
	 */
	public static List<Attribute> readRepairsFromDocument(String carID){
		if (repairNames.isEmpty()) {
			populateRepairNames();
		}
		
		ObjectId o = new ObjectId(carID);
		Bson filter = eq("_id", o);
		FindIterable<Document> myC = getCol().find().filter(filter);
		
		Document document = myC.cursor().next();
		
		List<Attribute> aList = new ArrayList<Attribute>();
		for(String x : repairNames) {
			String m = (String) document.get("repairs", Document.class).get(x, Document.class).get("mileage");
			String l = (String) document.get("repairs", Document.class).get(x, Document.class).get("last_update");
			Attribute att = new Attribute(x, m, l);
			aList.add(att);
		}
		
		return aList;
		
		
	}
	/**
	 * updates a specific attribute for the car
	 * @param att the attribute to update
	 * @param id the car ID to select the right one
	 */
	public static void updateAttribute(Attribute att, String id) {
		
		ObjectId o = new ObjectId(id);
		Bson filter = eq("_id", o);
		FindIterable<Document> myC = getCol().find().filter(filter);
		String number = att.getMileage().replace(",", "");
		Double numParsed = Double.parseDouble(number);
		Document query = new Document("$set", new Document("attributes." + att.getName() + ".mileage", String.format("%,.2f", numParsed)));
		getCol().updateOne(filter, query);
		Document query2 = new Document("$set", new Document("attributes." + att.getName() + ".last_update", att.getLastUpdated()));
		getCol().updateOne(filter, query2);
	
		
	}
	/**
	 * updates a specific repair for the car
	 * @param att the repair to update
	 * @param id the car ID to select the right one
	 */
	public static void updateRepair(Attribute att, String id) {
		
		ObjectId o = new ObjectId(id);
		Bson filter = eq("_id", o);
		FindIterable<Document> myC = getCol().find().filter(filter);
		String number = att.getMileage().replace(",", "");
		Double numParsed = Double.parseDouble(number);
		Document query = new Document("$set", new Document("repairs." + att.getName() + ".mileage", String.format("%,.2f", numParsed)));
		getCol().updateOne(filter, query);
		Document query2 = new Document("$set", new Document("repairs." + att.getName() + ".last_update", att.getLastUpdated()));
		getCol().updateOne(filter, query2);

	}
	/**
	 * updates the car with new info given
	 * @param car the new car info
	 * @param id the ID of the car to update
	 */
	public static void updateCar(Car car, String id) {
		System.out.println(id);
		ObjectId o = new ObjectId(id);
		Bson filter = eq("_id", o);
		String number = car.getMileage().replace(",", "");
		Double numParsed = Double.parseDouble(number);
		Document query = new Document("$set", new Document("make", car.getMake()));
		getCol().updateOne(filter, query);
		Document query2 = new Document("$set", new Document("model", car.getModel()));
		getCol().updateOne(filter, query2);
		Document query3 = new Document("$set", new Document("year", car.getYear()));
		getCol().updateOne(filter, query3);
		Document query4 = new Document("$set", new Document("mileage", String.format("%,.2f", numParsed)));
		getCol().updateOne(filter, query4);
		
	}
	
	

}
