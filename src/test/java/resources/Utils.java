package resources;

import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import TestDataPojo.TestData;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class Utils {
	public static RequestSpecification reqSpec, reqAuthSpec;
	public static PrintStream log;
	
	public io.restassured.specification.RequestSpecification RequestSpecification() throws IOException {
		if(reqSpec==null) {
			log = new PrintStream(new FileOutputStream("log.txt"));
			reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
											  .addHeader("Content-Type", "application/json")
											  .addFilter(RequestLoggingFilter.logRequestTo(log))
											  .addFilter(ResponseLoggingFilter.logResponseTo(log))
											  .build();
			return reqSpec;
		}
		return reqSpec;
	}
	
	public io.restassured.specification.RequestSpecification RequestAuthSpecification(String token) throws IOException {
		if(reqAuthSpec==null) {
			reqAuthSpec = new RequestSpecBuilder()
							.setBaseUri(getGlobalValue("baseUrl"))
							.addHeader("Cookie", "token="+token)
							.addHeader("Accept", "application/json")
							.addHeader("Content-Type", "application/json")
							.addFilter(RequestLoggingFilter.logRequestTo(log))
							.addFilter(ResponseLoggingFilter.logResponseTo(log))
							.build();
			return reqAuthSpec;
		}
		return reqAuthSpec;
	}
	
	public String getGlobalValue(String key) throws IOException {
		Properties prop=new Properties();
		File currentDir = new File("");
		String propertiesFilePath=currentDir.getAbsolutePath()+"\\src\\test\\java\\resources\\global.properties";
		FileInputStream fis = new FileInputStream(propertiesFilePath);
		prop.load(fis);
		return prop.getProperty(key);
		
	}
	
	public TestData inputData() throws StreamReadException, DatabindException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		File currentDir = new File("");
		String testDataFilePath=currentDir.getAbsolutePath()+"\\src\\test\\java\\resources\\TestData.json";
		TestData data = mapper.readValue(new File(testDataFilePath),TestData.class);
		return data;
	}
	
	

}
