package main.bean;

public class HotelBean {
	private String hotelId = null;
	private String roomId = null;
	private int roomNo;
	private String roomType=null;
	
	public HotelBean(){
		hotelId = null;
		roomId = null;
		roomNo = 0;
		roomType = null;
	}
	
	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	
	
}
