Feature: End to End Automation for Restful booker Api

	Scenario: Verify that the Restful Api service is Up
		Given The user prepares the health check API
		When The user calls "HealthCheckApi" with "Get" http request
		Then The Api call is success with status code "200"