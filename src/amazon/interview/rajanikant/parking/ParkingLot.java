package amazon.interview.rajanikant.parking;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Main Paringlot class which provides interafaces such as parkACar and
 * unparkACar
 * 
 * @author rajanikant malviya
 *
 */
public class ParkingLot {

	/**
	 * Map of cars for easy lookup while locating a parked car
	 */
	final protected HashMap<String, Car> parkedCars;

	/**
	 * Parking slot manager is the mainly responsible for space managing
	 * {@link ParkingSlotManager}
	 */
	final protected ParkingSlotManager parkingSlotManager;

	public ParkingLot(String[] parkingSpaceNames, int[] parkingSpaceDistances) {
		assert (parkingSpaceNames.length == parkingSpaceDistances.length);
		parkedCars = new HashMap<String, Car>();
		ArrayList<ParkingSlot> parkingSlots = new ArrayList<ParkingSlot>();
		for (int i = 0; i < parkingSpaceNames.length; i++) {
			parkingSlots.add(new ParkingSlot(parkingSpaceNames[i],
					parkingSpaceDistances[i]));
		}
		parkingSlotManager = new ParkingSlotManager(parkingSlots);
	}

	/**
	 * Get car instance (Parked) for given unique id
	 * 
	 * @param carUniqueId
	 * @return
	 */
	public Car getParkedCar(String carUniqueId) {
		return parkedCars.get(carUniqueId);
	}

	/**
	 * Utility method to lookup parking location of given car. Same can also be
	 * done by performaing getParkedCar(ID).getParkingSpace()
	 * 
	 * @param carUniqueId
	 * @return
	 */
	public ParkingSlot getParkingSlotForParkedCar(String carUniqueId) {
		return getParkedCar(carUniqueId).getParkingSlot();
	}

	/**
	 * Method to unpark a car
	 * 
	 * @param carUniqueIString
	 *            - unique car id (eg. Registration number/ owner contact
	 *            number)
	 */
	public void unparkACar(String carUniqueIString) {
		Car car = parkedCars.get(carUniqueIString);
		car.unpark();
	}

	/**
	 * Park a new incoming car
	 * 
	 * @param carUniqueId
	 *            - incoming car ID this would be used for car search while
	 *            locating the car back
	 * @throws NoEmptyParkingSpotAvailableException
	 *             in case all parking spaces are exausted
	 */
	public void parkACar(String carUniqueId)
			throws NoEmptyParkingSpotAvailableException {
		Car car = new Car(carUniqueId);
		ParkingSlot emptySlot = parkingSlotManager.findNearestEmptySlot();
		if (emptySlot == null) {
			throw new NoEmptyParkingSpotAvailableException();
		}
		car.park(emptySlot);
		parkedCars.put(carUniqueId, car);
	}

	public static void main(String[] args)
			throws NoEmptyParkingSpotAvailableException {
		ParkingLot parkingLot = new ParkingLot(new String[] { "A", "B", "C" },
				new int[] { 10, 2, 8 });
		// example to park car
		parkingLot.parkACar("carA");
		parkingLot.parkACar("carB");

		// ways to unpark a car
		// A.
		Car carA = parkingLot.getParkedCar("carA");
		carA.unpark();
		// B.
		parkingLot.unparkACar("carB");
	}
}
