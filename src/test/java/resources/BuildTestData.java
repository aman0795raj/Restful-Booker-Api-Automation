package resources;

import ReqPojo.AuthApiReqPojo;
import ReqPojo.BookingDates;
import ReqPojo.CreateBookingReqPojo;
import ReqPojo.PartialBookingReqPojo;
import TestDataPojo.BookingDetail;

public class BuildTestData {
	public AuthApiReqPojo TokenPayload(String username,String password) {
		AuthApiReqPojo AuthReqPayload = new AuthApiReqPojo();
		AuthReqPayload.setUsername(username);
		AuthReqPayload.setPassword(password);
		return AuthReqPayload;
	}
	
	public CreateBookingReqPojo CreateBookingPayload(String firstname,String lastname,String totalprice,boolean depositpaid,String checkin,String checkout,String additionalneeds) {
		CreateBookingReqPojo CreateBookingPayload = new CreateBookingReqPojo();
		BookingDates bookingDate=new BookingDates();
		CreateBookingPayload.setFirstname(firstname);
		CreateBookingPayload.setLastname(lastname);
		CreateBookingPayload.setTotalprice(totalprice);
		CreateBookingPayload.setDepositpaid(depositpaid);
		CreateBookingPayload.setAdditionalneeds(additionalneeds);
		bookingDate.setCheckin(checkin);
		bookingDate.setCheckout(checkout);
		CreateBookingPayload.setBookingdates(bookingDate);
		return CreateBookingPayload;
	}
	
	public PartialBookingReqPojo PartialBookingPayload(String firstname,String lastname) {
		PartialBookingReqPojo partialBookingDetails = new PartialBookingReqPojo();
		partialBookingDetails.setFirstname(firstname);
		partialBookingDetails.setLastname(lastname);
		return partialBookingDetails;
		
	}

}
