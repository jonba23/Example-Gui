import java.security.SecureRandom;
import java.util.*;


public class Owner {

	private String name;
	private final String id;
	private final List<Vehicle> cars;

	public Owner(String name, List<Vehicle> cars) {
		this.name = name;
		this.cars = cars;
		this.id = IDgenerator();
	}

	public Owner() {
		id = IDgenerator();
		cars = new ArrayList<Vehicle>();
	}

	public String IDgenerator() // 12 digit alphanumeric ID generator
	{
		String start = "";
		int count = 0;
		String hold = "";
		SecureRandom rand = new SecureRandom();
		char alphabet[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
				'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

		do {
			for (int i = 0; i < 3; i++) {
				hold = String.valueOf(rand.nextInt(999));
				start = start + alphabet[rand.nextInt(26)];
				if (hold.length() == 2) {
					hold = "0" + hold;
				} else if (hold.length() == 1) {
					hold = "00" + hold;
				}
			}
			start = start + hold;
			count++;
		} while (count < 2);
		return start;

	}

	public String getName() { // generated setters and getters
		return name;
	}

	public List<Vehicle> getCars() {
		return cars;
	}

	public void addCar(Vehicle var) {
		cars.add(var);
	}

	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCars(ArrayList<Vehicle> cars) {
		this.cars.clear();
		this.cars.addAll(cars);
	}

}
