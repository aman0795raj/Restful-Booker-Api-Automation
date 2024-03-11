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