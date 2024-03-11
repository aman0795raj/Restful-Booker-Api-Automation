Feature: End to End Automation for Restful booker Api

	Scenario: Verify that the Restful Booker Api service is Up
		Given The user prepares the health check API
		When The user calls "HealthCheckApi" with "Get" http request
		Then The HealthCheck Api call gives status code "201"
		
	Scenario: Verify that User gets an error on keeping the credential blank for token Api
		Given The user prepares token api with blank username and password
		When The user calls "AuthApi" with "Post" http request
		Then The Api call gives status code "200"
		And The response body gives an error as "Bad credentials"
		
	Scenario: Verify that Api call is success on providing valid username and password for token api
		Given The user prepares token api with valid username and password
		When The user calls "AuthApi" with "Post" http request
		Then The Api call gives status code "200"
		And The response of Auth Api contains token
		
	Scenario: Verify that the user is able to create a booking
		Given The user prepares booking api with valid details
		When The user calls "CreateBookingApi" with "Post" http request
		Then The Api call gives status code "200"
		And The Booking ID along with Booking details is displayed in response
		
#	Scenario: Booking Id is displayed in the response of GetBookingId
#		Given The user prepares GetBookingId api with valid details
#		When The user calls "BookingApi" with "Get" http request
#		Then The Api call gives status code "200"
#		And The BookingId generated in the previous step is visible in response

	Scenario: Verify that Booking details is displayed in the response of GetBooking details
		Given The user prepares GetBooking api with BookingId path parameter
		When The user calls "GetBookingApi" with "Get" http request and path params "id"
		Then The Api call gives status code "200"
		And The booking details provided is displayed in the response
		
	Scenario: Verify that User is able to update the entire Booking details with UpdateBooking Api
		Given The user prepares UpdateBooking api with BookingId path parameter
		When The user calls "UpdateBookingApi" with "Put" http request and path params "id"
		Then The Api call gives status code "200"
		And The Updated booking details provided is displayed in the response
		
	Scenario: Verify that user is able to update the username from booking details
		Given The user prepares PartialUpdateBooking api with BookingId path parameter
		When The user calls "PartialUpdateBookingApi" with "Patch" http request and path params "id"
		Then The Api call gives status code "200"
		And The username in the booking details gets updated
		
	Scenario: Verify that the user is able to delete the booking details
		Given The user prepares Delete Booking api with BookingId path parameter
		When The user calls "DeleteBookingApi" with "Delete" http request and path params "id"
		Then The Api call gives status code "201"