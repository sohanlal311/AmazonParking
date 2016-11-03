package amazon.interview.rajanikant.parking;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class ParkingSlotManagerTest {

	private ParkingSlot slotA;
	private ParkingSlotManager manager;
	private ParkingSlot slotB;
	private ParkingSlot slotC;
	private ParkingSlot slotD;

	@Before
	public void before(){
		slotA = new ParkingSlot("A", 15);
		slotB = new ParkingSlot("B", 20);
		slotC = new ParkingSlot("C", 10);
		slotD = new ParkingSlot("D", 12);
		ArrayList<ParkingSlot> list = new ArrayList<ParkingSlot>();
		list.add(slotA);
		list.add(slotB);
		list.add(slotC);
		list.add(slotD);
		manager = new ParkingSlotManager(list);
	}
	
	@Test
	public void testParkingSlotManagerShouldStoreParkingSlotsInSortedMannerByDefault(){
		Iterator<ParkingSlot> iterator = manager.emptySlots.iterator();
		assertEquals(slotC, iterator.next());
		assertEquals(slotD, iterator.next());
		assertEquals(slotA, iterator.next());
		assertEquals(slotB, iterator.next());
	}

	@Test
	public void testFindNearestEmptySlot() {
		assertTrue(slotC.getDistance()==manager.findNearestEmptySlot().getDistance());
		assertEquals(slotC, manager.findNearestEmptySlot());
		manager.emptySlots.remove(slotC);
		assertEquals(slotD, manager.findNearestEmptySlot());
		manager.emptySlots.add(slotC);
		assertEquals(slotC, manager.findNearestEmptySlot());
		manager.emptySlots.remove(slotC);
		manager.emptySlots.remove(slotD);
		assertEquals(slotA, manager.findNearestEmptySlot());
		manager.emptySlots.remove(slotA);
		manager.emptySlots.remove(slotB);
		assertNull(manager.findNearestEmptySlot());
		
	}

	@Test
	public void testParkingSlotOccupied() {
		slotD.occupy();
		assertTrue(manager.bookedSlots.contains(slotD));
		assertFalse(manager.emptySlots.contains(slotD));
		slotB.occupy();
		assertTrue(manager.bookedSlots.contains(slotB));
		assertFalse(manager.emptySlots.contains(slotB));
	}

	@Test
	public void testParkingSlotReleased() {
		slotD.occupy();
		slotA.occupy();
		slotB.occupy();
		slotD.vacate();
		assertFalse(manager.bookedSlots.contains(slotD));
		assertTrue(manager.emptySlots.contains(slotD));
	}

}
