package amazon.interview.rajanikant.parking;

import static org.junit.Assert.*;

import org.junit.Test;

public class CarTest {
	private String testCarUniqueId = "Test KA-0000";
	private String parkingSpaceId = "Dummy";

	@Test
	public void testCar() {
		Car car = new Car(testCarUniqueId);
		assertEquals(testCarUniqueId, car.getUniqueId());
	}

	@Test
	public void testPark() {
		Car car = new Car(testCarUniqueId);
		ParkingSlot slot = new ParkingSlot(parkingSpaceId, 10);
		car.park(slot);
		assertTrue(slot.isOccupied());
		assertSame(slot, car.getParkingSlot());
	}

	@Test
	public void testUnpark() {
		Car car = new Car(testCarUniqueId);
		ParkingSlot slot = new ParkingSlot(parkingSpaceId, 10);
		car.park(slot);
		car.unpark();
		assertFalse(slot.isOccupied());
		assertSame(null, car.getParkingSlot());
	}

}
