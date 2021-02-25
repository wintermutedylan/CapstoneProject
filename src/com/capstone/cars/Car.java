package com.capstone.cars;

public class Car {
	
	private String make;
	private String model;
	private String year;
	private String mileage;
	private String id;
	private String userId;
	
	public Car() {
		
	}
	
	public Car(String make, String model, String year, String mileage) {
		this.setMake(make);
		this.setModel(model);
		this.setYear(year);
		this.setMileage(mileage);
		this.userId = "user_id";
	}
	
	public Car(String make, String model, String year, String mileage, String id) {
		this.setMake(make);
		this.setModel(model);
		this.setYear(year);
		this.setMileage(mileage);
		this.setId(id);
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		String id = (getMake() + " " + getModel() + " " + getYear() + " " + getMileage() + " " + getId());
		return id;
	}
		

}
