package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.BuildTestData;
import resources.Endpoints;
import resources.Utils;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import ResPojo.AuthApiErrorResPojo;
import ResPojo.AuthApiResPojo;

public class Booker extends Utils {
	RequestSpecification reqSpec;
	Response res;
	Endpoints endpoint;
	BuildTestData data = new BuildTestData();
	AuthApiErrorResPojo authApiErrorRes;
	AuthApiResPojo authApiRes;
	public static String authToken;
	
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
	
}
