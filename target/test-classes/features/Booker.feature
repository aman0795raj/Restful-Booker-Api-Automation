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
		When The user calls "BookingApi" with "Post" http request
		Then The Api call gives status code "200"
		And The Booking ID along with Booking details is displayed in response