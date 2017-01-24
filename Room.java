public class Room {

	private String roomNum;
	private String roomType;
	private float roomPrice;
	private boolean hasBalcony;
	private boolean hasLounge;
	private String reservation;

	Room(String roomNum, String roomType, float roomPrice, boolean hasBalcony, boolean hasLounge,
			String reservation) {
		this.roomNum = roomNum;
		this.roomType = roomType;
		this.roomPrice = roomPrice;
		this.hasBalcony = hasBalcony;
		this.hasLounge = hasLounge;
		this.reservation = reservation;
	}

	public String getReservation() {
		return reservation;
	}

	public void setReservation(String reservation) {
		this.reservation = reservation;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public String getRoomType() {
		return roomType;
	}

	public float getRoomPrice() {
		return roomPrice;
	}

	public boolean isHasBalcony() {
		return hasBalcony;
	}

	public boolean isHasLounge() {
		return hasLounge;
	}

	
	public String toString() {
		return "Room Number:" + roomNum + ", Room Type:" + roomType + ", Price:£" + roomPrice + "0, Balcony:"
				+ hasBalcony + ", Lounge:" + hasLounge + ", Booking:" + reservation + "";
}
	public String toFile() {
		return "" + roomNum + " " + roomType + " " + roomPrice + "0 "
				+ hasBalcony + " " + hasLounge + " " + reservation + " ";
}
}