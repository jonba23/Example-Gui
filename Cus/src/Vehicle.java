public class Vehicle {
	
	private String make;
	private String year;
	private String vin;
	private long residency;

//	private long duration;
	
	public Vehicle(String make, String year, String vin, long residency) {
		this.make = make;
		this.year = year;
		this.vin = vin;
		this.residency = residency;
		
	}

	public String getMake() {
		return make;
	}

	public String getYear() {
		return year;
	}

	public String getVin() {
		return vin;
	}

	

	public long getResidency() {
		return residency;
	}
	
	//public long getDuration() {
	//	return duration;
	//}
	
}

