package com.capstone.cars;

import org.bson.Document;


public class CarConverter {
	
	
	/**
	 * 
	 * @param doc MongoDB document passed into to convert to Java object for easy manipulation
	 * @return returns a Car Java object
	 */
	public static Car toCar(Document doc) {
		Car c = new Car();
		c.setMake(doc.getString("make"));
		c.setModel(doc.getString("model"));
		c.setYear(doc.getString("year"));
		c.setMileage(doc.getString("mileage"));
		c.setId(doc.get("_id").toString());
		return c;
	}
	
	/**
	 * 
	 * @param car passes a Car Java object to convert to MongoDB document
	 * @return returns a new document ready to be stored in MongoDB
	 */
	public static Document toMongoDoc(Car car) {
		Document doc = new Document();
		doc.put("make", car.getMake());
		doc.put("model", car.getModel());
		doc.put("year", car.getYear());
		doc.put("mileage", car.getMileage());
		doc.put("id", car.getId());
		
		doc.append("attributes", DBInteract.addAttribute());
		doc.append("repairs", DBInteract.addRepairs());
		return doc;
	}

}
