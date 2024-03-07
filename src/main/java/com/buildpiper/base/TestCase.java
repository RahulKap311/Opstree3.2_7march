package com.buildpiper.base;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import com.buildpiper.report.Log;
import com.buildpiper.report.Recorder;

public class TestCase {

	String _timestamp;
	HashMap<String,String> _data=new HashMap<String,String>();
	HashMap<String,Object> _additionalData=new HashMap<String,Object>();
	String _testCaseName;
	String _testCaseModule;
	WebDriver driver;
	Recorder _screenRecorder;
	
	TestCase(String methodName, String packageName){
		Log.info("####### Starting  Test Case: " + methodName +" #######");	
		this._testCaseName=methodName;
		this._testCaseModule=packageName;
		try {
			this._screenRecorder=new Recorder(methodName);
		} catch (HeadlessException | IOException | AWTException e) {
			// TODO Auto-generated catch block
			Log.error("Unable to Start Recording");
			e.printStackTrace();
		}
	}
	public Recorder get_screenRecorder() {
		return _screenRecorder;
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	public String get_timestamp() {
		return _timestamp;
	}
	public void set_timestamp(String _timestamp) {
		this._timestamp = _timestamp;
	}
	public HashMap<String, String> get_data() {
		return _data;
	}
	public void set_data(HashMap<String, String> _data) {
		this._data = _data;
	}

	public void add_dataValue(String key, String value) {
		this._data.put(key, value);
	}
	
	public String get_dataValue(String key) {
		if((_data==null) || (_data.get(key)==null)) {Log.info("Test Data value not present in file @ correct location for "+key);}
		return this._data.get(key);
	}
	public String get_testCaseName() {
		return _testCaseName==null?"":_testCaseName;
	}
	public void set_testCaseName(String _testCaseName) {
		this._testCaseName = _testCaseName;
	}
	public String get_testCaseModule() {
		return _testCaseModule;
	}

	public void stopRecording() {
		// TODO Auto-generated method stub
		try {
			this._screenRecorder.stopRecording(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
