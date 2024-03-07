package com.buildpiper.capabilities;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.Assert;

import com.buildpiper.utils.Configuration;
import com.jayway.jsonpath.JsonPath;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APICapability {

	public RequestSpecification api_getUser() {

		RequestSpecification user= new RequestSpecBuilder()
				.setRelaxedHTTPSValidation()
				.setBaseUri(Configuration.get("apiBaseURI"))
				.setContentType(ContentType.JSON)
				.addHeader("Accept", ContentType.JSON.getAcceptHeader())
				.log(LogDetail.ALL)
				.build();
		return user;
	}

	public Response api_sendGetRequest(RequestSpecification requestspecification,String endpoint, Integer statusCode) {

		Response response= given(requestspecification).relaxedHTTPSValidation().get(endpoint);
		if(statusCode!=0) 
		{Assert.assertEquals(statusCode.intValue(), response.getStatusCode());}
		return response;

	} 

	public Response api_sendPostRequest(RequestSpecification requestspecification,String endpoint,String payload, Integer statusCode) {

		Response response= given(requestspecification).relaxedHTTPSValidation().body(payload).post(endpoint);
		if(statusCode!=0)
		{Assert.assertEquals(statusCode.intValue(), response.getStatusCode());}
		response.asString();
		return response;

	} 

	public Response api_sendPostRequest(RequestSpecification requestspecification,String fileName,Integer statusCode,String endpoint) {

		File file= new File(Configuration.get("userDir")+"\\uploadFiles\\"+fileName);
		Response response= given(requestspecification).relaxedHTTPSValidation().multiPart("file",file,"application/json").post(endpoint);
		if(statusCode!=0)
		{Assert.assertEquals(statusCode.intValue(), response.getStatusCode());}
		response.asString();
		return response;

	} 
	
	public Response api_sendDeleteRequest(RequestSpecification requestspecification,String endpoint,Integer statusCode) {

		Response response= given(requestspecification).relaxedHTTPSValidation().delete(endpoint);
		if(statusCode!=0)
		{Assert.assertEquals(statusCode.intValue(), response.getStatusCode());}
		response.asString();
		return response;
	} 

	public Response api_sendPutRequest(RequestSpecification requestspecification,String endpoint,String payload,Integer statusCode) {

		Response response= given(requestspecification).relaxedHTTPSValidation().body(payload).put(endpoint);
		if(statusCode!=0)
		{Assert.assertEquals(statusCode.intValue(), response.getStatusCode());}
		response.asString();
		return response;
	}
	
	public void api_validateResponse(Response actual,String expected) {

		JSONObject actualResponse= new JSONObject(actual.body().asString());
		JSONAssert.assertEquals(expected,actualResponse,false);
		
	}
	public void api_validateJsonPathValueEquals(Response response,String jsonPath ,String expectedValue) {

		Assert.assertTrue(JsonPath.parse(response.asString()).read(jsonPath).toString().equalsIgnoreCase(expectedValue));
		
	}
	
	public void api_validateJsonPathValueContains(Response response,String jsonPath ,String expectedValue) {

		response.then().body(jsonPath, Matchers.containsStringIgnoringCase(expectedValue));
	}

	public String api_getResponseDataJsonPath(Response response,String jsonPath) {

	return JsonPath.parse(response.asString()).read(jsonPath).toString();
	
	}
	
	public String api_getResponseDataJsonPath(String response,String jsonPath) {

		return JsonPath.parse(response).read(jsonPath).toString();
		
     }
	
	
	public void api_signIn(String url) {
		Map<String,String> params= new HashMap<>();
		params.put("Origin", url);
		RequestSpecification reqspec= api_getUser();
		reqspec.headers(params);
		String user=Configuration.get("user");
		String password=Configuration.get("password");
		String payload="{\"emailId\":\""+user+"\",\"password\":\""+password+"\"}";
		api_sendPostRequest(reqspec, "/api/um/user/signin", payload, null);

	}
	
}
