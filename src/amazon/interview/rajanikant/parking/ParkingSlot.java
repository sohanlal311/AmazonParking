package amazon.interview.rajanikant.parking;

import java.util.HashSet;

public class ParkingSlot {
	/**
	 * distance from entrance
	 */
	final private String id;
	private int distance;
	private boolean occupied;
	private HashSet<ParkingSlotListener> listeners;

	public ParkingSlot(String id, int distance) {
		this.id = id;
		this.distance = distance;
		this.listeners = new HashSet<ParkingSlotListener>();
	}

	public String getId() {
		return id;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void occupy() {
		this.occupied = true;
		for (ParkingSlotListener listener : listeners) {
			listener.parkingSlotOccupied(this);
		}
	}

	public int getDistance() {
		return distance;
	}

	public void vacate() {
		this.occupied = false;
		for (ParkingSlotListener listener : listeners) {
			listener.parkingSlotReleased(this);
		}
	}

	public void addListener(ParkingSlotListener listener) {
		listeners.add(listener);
	}

	public void removeListener(ParkingSlotListener listener) {
		listeners.remove(listener);
	}

}
