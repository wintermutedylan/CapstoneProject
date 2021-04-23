package com.capstone.cars;



public class Attribute implements Comparable<Attribute>{
	
	private String name;
	private String mileage;
	private String lastUpdated;
	
	
	public Attribute() {
		
	}
	
	public Attribute(String name, String mile, String last) {
		this.name = name;
		this.setMileage(mile);
		this.setLastUpdated(last);
		
	}

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		String id = (getName() + " " + getMileage() + " " + getLastUpdated());
		return id;
	}
	public int getMileInt() {
		String m = getMileage().replace(",", "");
		int i = Integer.parseInt(m);
		return i;
	}

	@Override
	public int compareTo(Attribute o) {
		
		return this.getMileInt() - o.getMileInt();
	}

}
