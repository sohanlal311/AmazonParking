package amazon.interview.rajanikant.parking;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {

	private ParkingLot parkingLot;
	private String carId1 = "carA";
	@Before
	public void init(){
		int[] parkingSpaceDistances = {5, 5, 3, 4, 7, 8, 2, 6, 7};
		String[] parkingSpaceNames = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
		parkingLot = new ParkingLot(parkingSpaceNames, parkingSpaceDistances);
	}
	
	@Test
	public void testGetParkedCar() throws NoEmptyParkingSpotAvailableException {
		parkingLot.parkACar(carId1);
		Car car = parkingLot.getParkedCar(carId1);
		assertEquals(carId1, car.getUniqueId());
	}

	@Test
	public void testGetParkingSlotForParkedCar() throws NoEmptyParkingSpotAvailableException {
		parkingLot.parkACar(carId1);
		Car car = parkingLot.getParkedCar(carId1);
		ParkingSlot slot = parkingLot.getParkingSlotForParkedCar(carId1);
		assertEquals(car.getParkingSlot(), slot);
	}

	@Test
	public void testUnparkACar() throws NoEmptyParkingSpotAvailableException {
		parkingLot.parkACar(carId1);
		ParkingSlot slot = parkingLot.getParkingSlotForParkedCar(carId1);
		parkingLot.unparkACar(carId1);
		assertNull(parkingLot.getParkedCar(carId1));
		parkingLot.parkACar("newCar");
		assertSame(slot, parkingLot.getParkingSlotForParkedCar("newCar"));
	}

	@Test
	public void testParkACar() throws NoEmptyParkingSpotAvailableException {
		parkingLot.parkACar(carId1);
		assertTrue(parkingLot.parkedCars.containsKey(parkingLot));
	}

}
