package com.buildpiper.report;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import com.buildpiper.base.Capability;
import com.buildpiper.base.TestCase;
import com.buildpiper.utils.Configuration;
import com.buildpiper.utils.StringUtility;
import com.buildpiper.utils.TimeUtililty;



public class Log extends Capability  {
	
    private static Logger Log =    LogManager.getLogger(Log.class.getName());
	
    public static void info (String message) {
        Log.info(message);
        Reporter.log(message, 0, true);
    
        
    }
    
    public static void info (String message,int level) {
    	try {
    	if(level<=Integer.valueOf(Configuration.get("logLevel").trim())) {
        	Log.info(message);
            Reporter.log(message, 0, true);
            saveScreenShot(_session);
        }else {Log.info(message);}
    	
        }
        catch(Exception e)
        {
        	System.out.println("Exception in Log "+e);
        }	
    }

    public static void error (String message) {
        Log.error(message);
        Reporter.log(message, 0, true);
        try {
        saveScreenShot(_session);}
        catch(Exception e)
        {
        	System.out.println("Exception in save screen");
        }
        Assert.assertTrue(false,message);
    }
 
    public static void debug (String message) {
        Log.debug(message);
    }

public static String saveScreenShot(TestCase _session) {
		if(_session==null) {return"";}
		String name=StringUtility.returnNullSafe(_session.get_testCaseName());
		String direc = System.getProperty("user.dir")+"/Reports/"+Configuration.get("executionid")+"/"+name+"_"+Configuration.get("browser");
		File destDir= new File(direc);
		name=name+"_"+Configuration.get("browser")+"_"+TimeUtililty.currentDateAndTime("DD_MMM_HHmmss");

		if (!destDir.exists())
			destDir.mkdir();

		File destPath = new File(destDir.getAbsolutePath()
				+ System.getProperty("file.separator") + name + ".jpg");
		try {if(_session.getDriver()==null) {return "";}
			FileUtils
			.copyFile(((TakesScreenshot) _session.getDriver())
					.getScreenshotAs(OutputType.FILE), destPath);
		} catch (IOException e) {
			Reporter.log(e.getMessage());
		}
		Reporter.log("<img src="+destPath.getAbsolutePath()+" alt='Lamp' width='500' height='400'>");
		//Reporter.log("<a href="+ destPath.getAbsolutePath() + " alt='alternative text'>Step</a>");	
		
		return destPath.getAbsolutePath();
	}

@Override
public void ui_selectValueFromDropDownByXPath(WebElement element, String value) {
	// TODO Auto-generated method stub
	
}

@Override
public void ui_selectValueFromDropDownByXPath(List<WebElement> selectRegistry, String value) {
	// TODO Auto-generated method stub
	
}

}