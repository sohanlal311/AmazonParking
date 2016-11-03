package amazon.interview.rajanikant.parking;

public interface ParkingSlotListener {

	void parkingSlotOccupied(ParkingSlot parkingSlot);

	void parkingSlotReleased(ParkingSlot parkingSlot);

}
