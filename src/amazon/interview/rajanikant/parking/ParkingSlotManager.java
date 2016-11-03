package amazon.interview.rajanikant.parking;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This is the main space/parking slot handling class. The main objective are as
 * below
 * 
 * a. Manage available/empty spots b. Keep all empty spots in sorted manner with
 * least distance from enterance at the top of the list c. Update empty and
 * booked list when car is actually parked or unparked
 * 
 * @author rajanikant
 *
 */
public class ParkingSlotManager implements ParkingSlotListener {

	/**
	 * TreeSet for keeping all empty slots in sorted order
	 */
	final protected TreeSet<ParkingSlot> emptySlots;
	/**
	 * 
	 */
	final protected HashSet<ParkingSlot> bookedSlots;
	private ReentrantLock lock = new ReentrantLock();

	public ParkingSlotManager(ArrayList<ParkingSlot> parkingSlots) {
		emptySlots = new TreeSet<ParkingSlot>(new Comparator<ParkingSlot>() {
			@Override
			public int compare(ParkingSlot slot1, ParkingSlot slot2) {
				return slot1.getDistance() - slot2.getDistance();
			}
		});
		bookedSlots = new HashSet<ParkingSlot>();
		emptySlots.addAll(parkingSlots);
		for (ParkingSlot parkingSlot : parkingSlots) {
			parkingSlot.addListener(this);
		}
	}

	/**
	 * Get nearest empty slot from available slots
	 * 
	 * @return
	 */
	public ParkingSlot findNearestEmptySlot() {
		if (emptySlots.isEmpty()) {
			return null;
		}
		lock.lock();
		Iterator<ParkingSlot> iterator = emptySlots.iterator();
		ParkingSlot slot = iterator.next();
		lock.unlock();
		return slot;
	}

	@Override
	public void parkingSlotOccupied(ParkingSlot parkingSlot) {
		lock.lock();
		emptySlots.remove(parkingSlot);
		bookedSlots.add(parkingSlot);
		lock.unlock();
	}

	@Override
	public void parkingSlotReleased(ParkingSlot parkingSlot) {
		lock.lock();
		emptySlots.add(parkingSlot);
		bookedSlots.remove(parkingSlot);
		lock.unlock();
	}

}
