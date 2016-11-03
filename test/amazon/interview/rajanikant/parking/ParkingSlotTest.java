package amazon.interview.rajanikant.parking;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParkingSlotTest {

	@Test
	public void testParkingSlot() {
		int distance = 30;
		ParkingSlot slot = new ParkingSlot(null, distance);
		assertTrue(distance == slot.getDistance());
	}

	@Test
	public void testHashCode() {
		int distance = 30;
		ParkingSlot slot = new ParkingSlot(null, distance);
		assertTrue(distance == slot.hashCode());
	}

	@Test
	public void testOccupy() {
		int distance = 30;
		ParkingSlot slot = new ParkingSlot(null, distance);
		TestListener listener = new TestListener();
		slot.addListener(listener);
		slot.occupy();
		assertTrue(listener.occupied);
		assertSame(slot, listener.slot);
	}

	@Test
	public void testVacate() {
		int distance = 30;
		ParkingSlot slot = new ParkingSlot(null, distance);
		TestListener listener = new TestListener();
		slot.addListener(listener);
		slot.vacate();
		assertTrue(listener.released);
		assertSame(slot, listener.slot);
	}

	class TestListener implements ParkingSlotListener {
		boolean released;
		boolean occupied;
		ParkingSlot slot;

		@Override
		public void parkingSlotReleased(ParkingSlot parkingSlot) {
			released = true;
			slot = parkingSlot;
		}

		@Override
		public void parkingSlotOccupied(ParkingSlot parkingSlot) {
			occupied = true;
			slot = parkingSlot;
		}
	};

}
