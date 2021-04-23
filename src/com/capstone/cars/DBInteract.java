package com.capstone.cars;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.FindIterable;

import java.util.List;
import java.util.Locale;

import static com.mongodb.client.model.Filters.eq;

import java.text.NumberFormat;
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
		
    	attributeNames.add("Oil Change");
    	attributeNames.add("Transmission Fluid Change");
    	attributeNames.add("Antifreeze (Coolant)");
    	attributeNames.add("Tire Rotation");
    	attributeNames.add("Tire Replacement");
    	attributeNames.add("Engine Air Filter");
    	attributeNames.add("Cabin Air Filter");
    	attributeNames.add("Windshield Wiper");
    	attributeNames.add("Rear Wiper");
    	attributeNames.add("Break Fluid Change");
    	attributeNames.add("Spark Plugs");
    	attributeNames.add("Power Steering Fluid Change");
    	attributeNames.add("Battery");
    	attributeNames.add("Timing Belt");
    	attributeNames.add("Safety Inspection");
    	attributeNames.add("Emmissions Inspection");
    	attributeNames.add("Car Wash");
		
	}
	private static void populateRepairNames() {
		
    	repairNames.add("Brake Pads - Front");
    	repairNames.add("Brake Pads - Rear");
    	repairNames.add("Brake Rotors - Front");
    	repairNames.add("Brake Rotors - Rear");
    	repairNames.add("Brake Calipers - Front");
    	repairNames.add("Brake Calipers - Rear");
    	repairNames.add("Shock Absorber/Strut - Front");
    	repairNames.add("Shock Absorber/Strut - Rear");
    	repairNames.add("Ball Joints - Front");
    	repairNames.add("Ball Joints - Rear");
    	repairNames.add("Tie Rods - Front");
    	repairNames.add("Tie Rods - Rear");
    	repairNames.add("Head Lights");
    	repairNames.add("Turn Signals");
    	repairNames.add("Tail Lights");
    	repairNames.add("Brake Lights");
    	repairNames.add("Interior Lights");
    	repairNames.add("Windshield Replacement");
    	repairNames.add("Side glass Replacement");
    	repairNames.add("Rear glass Replacement");
    	repairNames.add("Brake Shoes");
    	repairNames.add("Serpentine Belt");
    	repairNames.add("Thermostat");
    	repairNames.add("Water Pump");
    	repairNames.add("Oxygen Sensors");
    	repairNames.add("Muffler");
    	repairNames.add("Catalytic Converter");
    	repairNames.add("Tail Pipe");
    	repairNames.add("Transmission Repair");
    	
		
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
		int numParsed = Integer.parseInt(number);
		Document query = new Document("$set", new Document("attributes." + att.getName() + ".mileage", NumberFormat.getNumberInstance(Locale.US).format(numParsed)));
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
		int numParsed = Integer.parseInt(number);
		Document query = new Document("$set", new Document("repairs." + att.getName() + ".mileage", NumberFormat.getNumberInstance(Locale.US).format(numParsed)));
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
		int numParsed = Integer.parseInt(number);
		Document query = new Document("$set", new Document("make", car.getMake()));
		getCol().updateOne(filter, query);
		Document query2 = new Document("$set", new Document("model", car.getModel()));
		getCol().updateOne(filter, query2);
		Document query3 = new Document("$set", new Document("year", car.getYear()));
		getCol().updateOne(filter, query3);
		Document query4 = new Document("$set", new Document("mileage", NumberFormat.getNumberInstance(Locale.US).format(numParsed)));
		getCol().updateOne(filter, query4);
		
	}
	
	

}
