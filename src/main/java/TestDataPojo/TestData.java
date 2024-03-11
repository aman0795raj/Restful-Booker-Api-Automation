package TestDataPojo;

public class TestData {
	private String username;
	private String password;
	private BookingDetail bookingDetails;
	private BookingDetail updatedBookingDetails;
	private String updatedFirstname;
	private String updatedLastname;
	
	public String getUpdatedFirstname() {
		return updatedFirstname;
	}
	public void setUpdatedFirstname(String updatedFirstname) {
		this.updatedFirstname = updatedFirstname;
	}
	public String getUpdatedLastname() {
		return updatedLastname;
	}
	public void setUpdatedLastname(String updatedLastname) {
		this.updatedLastname = updatedLastname;
	}
	public BookingDetail getUpdatedBookingDetails() {
		return updatedBookingDetails;
	}
	public void setUpdatedBookingDetails(BookingDetail updatedBookingDetails) {
		this.updatedBookingDetails = updatedBookingDetails;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public BookingDetail getBookingDetails() {
		return bookingDetails;
	}
	public void setBookingDetails(BookingDetail bookingDetails) {
		this.bookingDetails = bookingDetails;
	}
	

}
