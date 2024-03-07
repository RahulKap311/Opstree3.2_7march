package com.buildpiper.utils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;

import com.buildpiper.report.Log;

public class TimeUtililty {
	
	
	public static String currentDateAndTime(String format) {
		StringUtility.isNotEmpty(format);
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
		String date = now.format(dtf);
		return date;
	}
	
	public static String addedMinutesTimes(String format,int n) {
	    StringUtility.isNotEmpty(format);
	    LocalDateTime now = LocalDateTime.now().plusMinutes(n);  // Add n minutes to current time
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
	    String date = now.format(dtf);
	    return date;
	}

	public static boolean CleanFolder(String destination) {
		try {
			File destDir= new File(destination);
			if(destDir.exists())
			{FileUtils.cleanDirectory(new File(destination));}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	return true;
	}
	
	public static boolean CopyDirectory(String srcDir,String destDir) {
		
		try {
			File source= new File(srcDir);
			File destination= new File(destDir);
			if(destination.exists()){FileUtils.deleteDirectory(destination);}
			if(!source.exists()) {Log.info(String.format("Unable to copy final report, Source Folder  %s does not exist",srcDir),2);
			return false;}
			if(!destination.exists()) {
			FileUtils.copyDirectory(source, destination);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.info(String.format("Unable to copy final report, Getting error  %s", e.getMessage()),2);
			return false;
		}
	return true;
	}
	
	
	
}
