package com.buildpiper.capabilities;

import java.util.List;

import org.openqa.selenium.WebElement;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public interface apicapabilities {
	
	public RequestSpecification api_getUser();
	public Response api_sendGetRequest(RequestSpecification requestspecification,String endpoint, Integer statusCode); 
	public Response api_sendPostRequest(RequestSpecification requestspecification,String endpoint,String payload, Integer statusCode); 
	public Response api_sendPostRequest(RequestSpecification requestspecification,String fileName,Integer statusCode,String endpoint); 
	public Response api_sendDeleteRequest(RequestSpecification requestspecification,String endpoint,Integer statusCode);
	public Response api_sendPutRequest(RequestSpecification requestspecification,String endpoint,String payload,Integer statusCode) ;
	public void api_validateResponse(Response actual,String expected);
	public void api_validateJsonPathValueEquals(Response response,String jsonPath ,String expectedValue);
	public void api_validateJsonPathValueContains(Response response,String jsonPath ,String expectedValue);
    public String api_getResponseDataJsonPath(Response response,String jsonPath);
	public String api_getResponseDataJsonPath(String response,String jsonPath);
	public void api_signIn(String url);
	void ui_selectValueFromDropDownByXPath(List<WebElement> selectRegistry, String value);

}
