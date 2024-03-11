package ResPojo;

import TestDataPojo.BookingDetail;

public class CreateBookingResPojo {
	private int bookingid;
	private BookingDetail booking;
	private String additionalneeds;
	public int getBookingid() {
		return bookingid;
	}
	public void setBookingid(int bookingid) {
		this.bookingid = bookingid;
	}
	public BookingDetail getBooking() {
		return booking;
	}
	public void setBooking(BookingDetail booking) {
		this.booking = booking;
	}
	public String getAdditionalneeds() {
		return additionalneeds;
	}
	public void setAdditionalneeds(String additionalneeds) {
		this.additionalneeds = additionalneeds;
	}

}
