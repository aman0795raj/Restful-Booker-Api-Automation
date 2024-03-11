package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import resources.BuildTestData;
import resources.Endpoints;
import resources.Utils;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import ResPojo.AuthApiErrorResPojo;
import ResPojo.AuthApiResPojo;
import ResPojo.CreateBookingResPojo;
import ResPojo.GetBookingId;
import ResPojo.GetBookingIdResPojo;
import TestDataPojo.BookingDetail;

public class Booker extends Utils {
	RequestSpecification reqSpec;
	Response res;
	Endpoints endpoint;
	BuildTestData data = new BuildTestData();
	AuthApiErrorResPojo authApiErrorRes;
	AuthApiResPojo authApiRes;
	public static String authToken;
	public static int bookingId;
	CreateBookingResPojo CreateBookingRes;
	GetBookingIdResPojo GetBookingIdRes;
	
	//Scenario: Verify that the Restful Booker Api service is Up
	@Given("The user prepares the health check API")
	public void the_user_prepares_the_health_check_api() throws IOException {
		reqSpec=given().spec(RequestSpecification());
	    
	}
	@When("The user calls {string} with {string} http request")
	public void the_user_calls_with_http_request(String resource, String httpMethod) {
		endpoint=Endpoints.valueOf(resource);
		if(httpMethod.equalsIgnoreCase("get")) {
			res=reqSpec.when().get(endpoint.getEndpoint());
		}
		else if(httpMethod.equalsIgnoreCase("post")) {
			res=reqSpec.when().post(endpoint.getEndpoint());
		}
	    
	}
	@Then("The HealthCheck Api call gives status code {string}")
	public void the_HealthCheck_api_call_gives_status_code(String statusCode) {
		if (res.getStatusCode() != Integer.parseInt(statusCode)) {
		    System.out.println("Health check failed. Terminating other API calls.");
		    throw new RuntimeException("Health check failed. Terminating other API calls.");
		} else {
			assertEquals(true,true);
		}
	}
	
	//Scenario: Verify that User gets an error on keeping the credential blank for token Api
	@Given("The user prepares token api with blank username and password")
	public void the_user_prepares_token_api_with_blank_username_and_password() throws IOException {
		reqSpec=given().spec(RequestSpecification()).body(data.TokenPayload("", ""));
	}
	
	@Then("The Api call gives status code {string}")
	public void the_api_call_gives_status_code(String statusCode) {
		assertEquals(res.getStatusCode(),Integer.parseInt(statusCode));
	}
	
	@Then("The response body gives an error as {string}")
	public void the_response_body_gives_an_error_as(String errorMessage) {
		authApiErrorRes=res.as(AuthApiErrorResPojo.class);
		assertEquals(authApiErrorRes.getReason(),errorMessage);
		
	}
	
	//Scenario: Verify that Api call is success on providing valid username and password for token api
	@Given("The user prepares token api with valid username and password")
	public void the_user_prepares_token_api_with_valid_username_and_password() throws StreamReadException, DatabindException, IOException {
	    reqSpec=given().spec(RequestSpecification()).body(data.TokenPayload(inputData().getUsername(),inputData().getPassword() ));
	}
	@Then("The response of Auth Api contains token")
	public void the_response_of_auth_api_contains_token() {
	   authApiRes=res.as(AuthApiResPojo.class);
	   authToken=authApiRes.getToken();
	   System.out.println("Token: "+authToken);
	}
	
	//Scenario: Verify that the user is able to create a booking
	@Given("The user prepares booking api with valid details")
	public void the_user_prepares_booking_api_with_valid_details() throws StreamReadException, DatabindException, IOException {
		reqSpec=given().spec(RequestSpecification()).body(
				data.CreateBookingPayload(
							inputData().getBookingDetails().getFirstname(),
							inputData().getBookingDetails().getLastname(),
							inputData().getBookingDetails().getTotalprice(), 
							inputData().getBookingDetails().getDepositpaid(),
							inputData().getBookingDetails().getBookingdates().getCheckin(),
							inputData().getBookingDetails().getBookingdates().getCheckout(),
							inputData().getBookingDetails().getAdditionalneeds()));
							
					    
	}
	@Then("The Booking ID along with Booking details is displayed in response")
	public void the_booking_id_along_with_booking_details_is_displayed_in_response() {
		CreateBookingRes=res.as(CreateBookingResPojo.class);
		bookingId=CreateBookingRes.getBookingid();
		System.out.println("Booking ID: "+bookingId);
	}
	
	//Scenario: Booking Id is displayed in the response of GetBookingId
	@Given("The user prepares GetBookingId api with valid details")
	public void the_user_prepares_get_booking_id_api_with_valid_details() throws IOException {
	    reqSpec=given().spec(RequestSpecification());
	}
	@Then("The BookingId generated in the previous step is visible in response")
	public void the_booking_id_generated_in_the_previous_step_is_visible_in_response() {
		GetBookingIdRes=res.as(GetBookingIdResPojo.class);
		for(GetBookingId bookingIdObj : GetBookingIdRes.getBookingsId()) {
			System.out.println(bookingIdObj.getBookingid());
		}
		
	}
	
	//Scenario: Verify that Booking details is displayed in the response of GetBooking details
	
	@Given("The user prepares GetBooking api with BookingId path parameter")
	public void the_user_prepares_get_booking_api_with_booking_id_path_parameter() throws IOException {
	    reqSpec=given().spec(RequestSpecification()).pathParam("id",bookingId );
	}
	
	@When("The user calls {string} with {string} http request and path params {string}")
	public void the_user_calls_with_http_request_and_path_params(String resource, String httpMethod, String pathParam) {
		endpoint=Endpoints.valueOf(resource);
		if(httpMethod.equalsIgnoreCase("get")) {
			res=reqSpec.when().get(endpoint.getEndpoint()+"/{"+pathParam+"}");
		}
		else if(httpMethod.equalsIgnoreCase("put")) {
			res=reqSpec.when().put(endpoint.getEndpoint()+"/{"+pathParam+"}");
		}
		else if(httpMethod.equalsIgnoreCase("patch")) {
			res=reqSpec.when().patch(endpoint.getEndpoint()+"/{"+pathParam+"}");
		}
		else if(httpMethod.equalsIgnoreCase("delete")) {
			res=reqSpec.when().delete(endpoint.getEndpoint()+"/{"+pathParam+"}");
		}
	}
	@Then("The booking details provided is displayed in the response")
	public void the_booking_details_provided_is_displayed_in_the_response() throws StreamReadException, DatabindException, IOException {
		TestDataPojo.BookingDetail bookingDetail=res.as(TestDataPojo.BookingDetail.class);
		assertEquals(bookingDetail.getFirstname(),inputData().getBookingDetails().getFirstname());
		assertEquals(bookingDetail.getLastname(),inputData().getBookingDetails().getLastname());
		assertEquals(bookingDetail.getTotalprice(),inputData().getBookingDetails().getTotalprice());
		assertEquals(bookingDetail.getDepositpaid(),inputData().getBookingDetails().getDepositpaid());
		assertEquals(bookingDetail.getAdditionalneeds(),inputData().getBookingDetails().getAdditionalneeds());
		assertEquals(bookingDetail.getBookingdates().getCheckout(),inputData().getBookingDetails().getBookingdates().getCheckout());
		assertEquals(bookingDetail.getBookingdates().getCheckin(),inputData().getBookingDetails().getBookingdates().getCheckin());
	}
	
	//Scenario: Verify that User is able to update the entire Booking details with UpdateBooking Api
	@Given("The user prepares UpdateBooking api with BookingId path parameter")
	public void the_user_prepares_update_booking_api_with_booking_id_path_parameter() throws IOException {
	    reqSpec=given().spec(RequestAuthSpecification(authToken)).pathParam("id", bookingId).body(
	    		data.CreateBookingPayload(
	    				inputData().getUpdatedBookingDetails().getFirstname(),
						inputData().getUpdatedBookingDetails().getLastname(),
						inputData().getUpdatedBookingDetails().getTotalprice(), 
						inputData().getUpdatedBookingDetails().getDepositpaid(),
						inputData().getUpdatedBookingDetails().getBookingdates().getCheckin(),
						inputData().getUpdatedBookingDetails().getBookingdates().getCheckout(),
						inputData().getUpdatedBookingDetails().getAdditionalneeds()
	    				));
	}
	@Then("The Updated booking details provided is displayed in the response")
	public void the_updated_booking_details_provided_is_displayed_in_the_response() throws StreamReadException, DatabindException, IOException {
		TestDataPojo.BookingDetail bookingDetail=res.as(TestDataPojo.BookingDetail.class);
		assertEquals(bookingDetail.getFirstname(),inputData().getUpdatedBookingDetails().getFirstname());
		assertEquals(bookingDetail.getLastname(),inputData().getUpdatedBookingDetails().getLastname());
		assertEquals(bookingDetail.getTotalprice(),inputData().getUpdatedBookingDetails().getTotalprice());
		assertEquals(bookingDetail.getDepositpaid(),inputData().getUpdatedBookingDetails().getDepositpaid());
		assertEquals(bookingDetail.getAdditionalneeds(),inputData().getUpdatedBookingDetails().getAdditionalneeds());
		assertEquals(bookingDetail.getBookingdates().getCheckout(),inputData().getUpdatedBookingDetails().getBookingdates().getCheckout());
		assertEquals(bookingDetail.getBookingdates().getCheckin(),inputData().getUpdatedBookingDetails().getBookingdates().getCheckin());
	}
	
	//Scenario: Verify that user is able to update the username from booking details
	@Given("The user prepares PartialUpdateBooking api with BookingId path parameter")
	public void the_user_prepares_partial_update_booking_api_with_booking_id_path_parameter() throws StreamReadException, DatabindException, IOException {
	    reqSpec=given().spec(RequestAuthSpecification(authToken)).pathParam("id", bookingId).body(
	    		data.PartialBookingPayload(
	    				inputData().getUpdatedFirstname(),
	    				inputData().getUpdatedLastname()
	    				)
	    		);
	    		
	}
	@Then("The username in the booking details gets updated")
	public void the_username_in_the_booking_details_gets_updated() throws StreamReadException, DatabindException, IOException {
		BookingDetail bookingDetail=res.as(BookingDetail.class);
		assertEquals(bookingDetail.getFirstname(),inputData().getUpdatedFirstname());
		assertEquals(bookingDetail.getLastname(),inputData().getUpdatedLastname());
	}
	
	//Scenario: Verify that the user is able to delete the booking details
	@Given("The user prepares Delete Booking api with BookingId path parameter")
	public void the_user_prepares_delete_booking_api_with_booking_id_path_parameter() throws IOException {
		reqSpec=given().spec(RequestAuthSpecification(authToken)).pathParam("id", bookingId);
	}
}
