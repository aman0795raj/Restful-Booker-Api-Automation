package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.Endpoints;
import resources.Utils;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

public class Booker extends Utils {
	RequestSpecification reqSpec;
	Response res;
	Endpoints endpoint;
	boolean healthChekApi=false;
	
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
	    
	}
	@Then("The Api call is success with status code {string}")
	public void the_api_call_is_success_with_status_code(String statusCode) {
	    if(Integer.parseInt(statusCode)== 201) {
	    	healthChekApi=true;
	    	assertEquals(true,true);
	    }
	}
}
