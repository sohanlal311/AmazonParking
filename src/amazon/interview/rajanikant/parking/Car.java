package amazon.interview.rajanikant.parking;

public class Car {

	private final String uniqueId;

	private ParkingSlot parkingSlot;

	public Car(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public void park(ParkingSlot slot) {
		slot.occupy();
		parkingSlot = slot;
	}

	public void unpark() {
		parkingSlot.vacate();
		parkingSlot = null;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public ParkingSlot getParkingSlot() {
		return this.parkingSlot;
	}

}
