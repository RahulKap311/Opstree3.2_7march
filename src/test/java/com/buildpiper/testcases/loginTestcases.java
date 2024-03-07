package com.buildpiper.testcases;

import java.util.ArrayList;

import org.aeonbits.owner.ConfigFactory;
import org.json.simple.JSONObject;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.buildpiper.base.BaseTest;
import com.buildpiper.pages.LoginPage;
import com.buildpiper.utils.ExcelUtility;
import com.buildpiper.utils.FrameworkConfig;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

@Listeners(com.buildpiper.report.ExtentReportListener.class)


public class loginTestcases extends BaseTest {
	
	FrameworkConfig config = ConfigFactory.create(FrameworkConfig.class);

	ExcelUtility reader = new ExcelUtility();

	@Test(groups = { "Regression" }, priority = 0)
	public void loginTest() {
       //----------------------------------Login API-----------------------
		RequestSpecification requestSpec=RestAssured.given();
		requestSpec.baseUri("http://122.160.30.218:17901");
		requestSpec.basePath("/api/v1/user/login/");
		JSONObject logincredential=new JSONObject();
		logincredential.put("email", "opstree@opstree.com");
		logincredential.put("password", "Opstree@12345");
		requestSpec.contentType(ContentType.JSON);
		requestSpec.body(logincredential.toJSONString());
		io.restassured.response.Response res=requestSpec.post();
		String body=res.getBody().asString();
		JsonPath jsonpath=new JsonPath(body);
		String access=jsonpath.get("access");
		
		//System.out.println(res.getBody().asString());
		System.out.println("Status Code of login API:"+res.getStatusCode());
		//-----------------------------------DashBoad API----------------------
		
		RequestSpecification requestSpec1=RestAssured.given();
		requestSpec1.baseUri("http://122.160.30.218:17901");
		requestSpec1.basePath("/api/v1/project/dashboard/");
		requestSpec1
		       .header("Authorization","Bearer "+access)
		       .contentType(ContentType.JSON);
		
		io.restassured.response.Response res1=requestSpec1.get();
		String body1=res1.getBody().asString();
		
		JsonPath jsonpath1=new JsonPath(body1);
		ArrayList<String> projectname = new ArrayList<String>();
		 projectname=jsonpath1.get("results.components.name");
		
		System.out.println("Status Code of DashBoard API:"+res1.getStatusCode());
	    
	    String projectnameArray[]=projectname.toString().replace("[[", "").split(",");
	    System.out.println("Project Name:");
	    for(int i=0;i<projectnameArray.length-1;i++) 
	    {
	    	System.out.println(projectnameArray[i]); 
	    }    	
	   
	    System.out.println("Project Count:"+projectnameArray.length);
		
	}
	
	/*		RequestSpecification requestSpec=RestAssured.given();
		requestSpec.baseUri("https://api.buildpiper.io");
		requestSpec.basePath("/api/v1/user/login/");
		JSONObject logincredential=new JSONObject();
		logincredential.put("email", "opstree@opstree.com");
		logincredential.put("password", "Tree@Jul2023");
		requestSpec.contentType(ContentType.JSON);
		requestSpec.body(logincredential.toJSONString());
		io.restassured.response.Response res=requestSpec.post();
		String body=res.getBody().asString();
		JsonPath jsonpath=new JsonPath(body);
		String access=jsonpath.get("access");
		
		System.out.println(res.getBody().asString());
		System.out.println(res.getStatusCode());
		System.out.println(access);
		//-----------------------------------
		
		RequestSpecification requestSpec1=RestAssured.given();
		requestSpec1.baseUri("https://api.buildpiper.io");
		requestSpec1.basePath("/api/v1/project/dashboard/");
		requestSpec1
		       .header("Authorization","Bearer "+access)
		       .contentType(ContentType.JSON);
		
		io.restassured.response.Response res1=requestSpec1.get();
		String body1=res1.getBody().asString();
		
		JsonPath jsonpath1=new JsonPath(body1);
		ArrayList<String> projectname = new ArrayList<String>();
		 projectname=jsonpath1.get("results.components.name[1]");
		
		System.out.println(res1.getBody().asString());
		System.out.println(res1.getStatusCode());
		System.out.println(projectname);
		//---------------------------------------------
	
		RequestSpecification requestSpec2=RestAssured.given();
		requestSpec2.baseUri("https://api.buildpiper.io");
		requestSpec2.basePath("/api/v1/project/1084/pipeline/recent/activity/");
		requestSpec2
		       .header("Authorization","Bearer "+access)
		       .contentType(ContentType.JSON);
		
		io.restassured.response.Response res2=requestSpec2.get();
		String body2=res2.getBody().asString();
		
		JsonPath jsonpath2=new JsonPath(body2);
		ArrayList<String> name = new ArrayList<String>();
		name=jsonpath1.get("results.components");
		
		System.out.println(res2.getBody().asString());
		System.out.println(res2.getStatusCode());
		System.out.println(name);*/

}
