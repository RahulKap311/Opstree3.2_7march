package com.buildpiper.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.buildpiper.report.Log;
import com.google.common.io.Resources;



@SuppressWarnings("deprecation")
public class ExcelUtility {

	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	
	private Object[][] dataIncludingHeaders;

	public ExcelUtility(String sheetName) {
		try {
			String filePath= System.getProperty("user.dir")+Configuration.get("testDataLocation");
			FileInputStream excelFile=new FileInputStream(new File(filePath));
			workbook = new XSSFWorkbook(excelFile);
			this.sheet = workbook.getSheet(sheetName);
			load();
		} catch (IOException e) {
			throw new RuntimeException("Error while reading workbook", e);
		}
	}
	/**
	 * Default sheet is first sheet from workbook
	 * @param filePath
	 */
	public ExcelUtility() {
		try {
			if(Configuration.get("environment").equals("pt") || Configuration.get("environment").equals("perfeasy")) {
				String filePath= System.getProperty("user.dir")+Configuration.get("testDataLocation");
				FileInputStream excelFile=new FileInputStream(new File(filePath));
				workbook = new XSSFWorkbook(excelFile);
				this.sheet = workbook.getSheetAt(0);
				load();	
			}
			if(Configuration.get("environment").equals("sandbox")) {
				String filePath= System.getProperty("user.dir")+Configuration.get("testDataLocation5");
				FileInputStream excelFile=new FileInputStream(new File(filePath));
				workbook = new XSSFWorkbook(excelFile);
				this.sheet = workbook.getSheetAt(0);
				load();	
			}
			if(Configuration.get("environment").equals("lenskart")) {
				String filePath= System.getProperty("user.dir")+Configuration.get("testDataLocation1");
				FileInputStream excelFile=new FileInputStream(new File(filePath));
				workbook = new XSSFWorkbook(excelFile);
				this.sheet = workbook.getSheetAt(0);
				load();	
			}
			if(Configuration.get("environment").equals("sandbox1")) {
				String filePath= System.getProperty("user.dir")+Configuration.get("testDataLocation2");
				FileInputStream excelFile=new FileInputStream(new File(filePath));
				workbook = new XSSFWorkbook(excelFile);
				this.sheet = workbook.getSheetAt(0);
				load();	
			}
			if(Configuration.get("environment").equals("demo")) {
				String filePath= System.getProperty("user.dir")+Configuration.get("testDataLocation3");
				FileInputStream excelFile=new FileInputStream(new File(filePath));
				workbook = new XSSFWorkbook(excelFile);
				this.sheet = workbook.getSheetAt(0);
				load();	
			}
			if(Configuration.get("environment").equals("uolo")) {
				String filePath= System.getProperty("user.dir")+Configuration.get("testDataLocation4");
				FileInputStream excelFile=new FileInputStream(new File(filePath));
				workbook = new XSSFWorkbook(excelFile);
				this.sheet = workbook.getSheetAt(0);
				load();	
			}
	
		} catch (IOException e) {
			Log.info("");
			throw new RuntimeException("Exception while reading workbook", e);
		}
	}


	public Object getValue( int rowIndex, int colIndex) {
		Object value = null;
		Cell c = sheet.getRow(rowIndex).getCell(colIndex);
		if (c==null){
			return null;
		}
		switch (c.getCellTypeEnum()) {
		case STRING:
			value = c.getStringCellValue();
			break;
		case NUMERIC:
			value = c.getNumericCellValue();
			if(HSSFDateUtil.isCellDateFormatted(c)){
				value = c.getDateCellValue();
			}
			break;
		case _NONE:
		case BLANK:
			value = "";
			break;
		case ERROR:
			value = "#ERR#";
			break;
		case BOOLEAN:
			value = c.getBooleanCellValue();
			break;
		case FORMULA:
			value = c.getCellFormula();
			break;
		default:
			break;
		}
		return value;
	}
	private Row getRow(int rowIndex) {
		return sheet.getRow(rowIndex);
	}
	void closeReading() {
		try {
			workbook.close();
		} catch (IOException e) {
			// XXX: ignore  for now
			e.printStackTrace();
		}
	}
	public void load(){
		int lastRowNumber = sheet.getLastRowNum() ;
		int colCount = getRow(0).getLastCellNum();
		dataIncludingHeaders = new Object[lastRowNumber+1][colCount];
		for (int i = 0; i < lastRowNumber+1; i++) {
			for (int j = 0; j < colCount; j++) {
				dataIncludingHeaders[i][j] =getValue(i, j);
			}
		}
	}

	public Object[][] read() {
		Object[][] data = new Object[dataIncludingHeaders.length-1][];
		if(dataIncludingHeaders.length<2){
			throw new RuntimeException("No Data found in excel file");
		}
		// Remove headers
		for (int i = 0; i < dataIncludingHeaders.length-1; i++) {
			data[i]=dataIncludingHeaders[i+1];
		}
		return data;
	}


	public synchronized Map<String, String> getTestData(String testcase) {
     Log.info("Getting test data for "+testcase, 2);
     System.out.println();
		Object[][] data = getDataMap();

		for (int i = 0; i < data.length; i++) {
			@SuppressWarnings("unchecked")
			Map<String,String> map = (Map<String,String>)data[i][0];
			Set<String> keys = map.keySet();
			for (String key : keys) {
				String value = map.get(key);
				if(key.equalsIgnoreCase("TestCaseName") && value.trim().equalsIgnoreCase(testcase.trim()))
				{   
					if(value!=null && !value.isEmpty() && value.contains("<RANDOM>")){
						String newValue = map.get(key).replace("<RANDOM>", UUID.randomUUID().toString().split("-")[0]);
						map.put(key,newValue);
					}
					return map;}
			}

		}
		Log.info("No testdata found for Test Case: "+testcase);
		System.out.println(data);

		return null;
	}

	public Object[][] getDataMap() {
		//List<Map<String, String>> data = new ArrayList<Map<String,String>>();
		Object[][] dataMap = new Object[dataIncludingHeaders.length-1][1];
		if(dataIncludingHeaders.length<2){
			throw new RuntimeException("No Data fround in excel file");
		}
		Object[] headers = dataIncludingHeaders[0];
		for (int i = 1; i < dataIncludingHeaders.length; i++) {
			Map<String, String> mapData = new LinkedHashMap<String,String>();
			for (int j = 0; j < dataIncludingHeaders[i].length; j++) {
				mapData.put(headers[j].toString(), toStringValue(dataIncludingHeaders[i][j]));
			}
			dataMap[i-1][0] = mapData;
		}
		return dataMap;
	}
	
	private XSSFRow row = null;
	private XSSFCell cell = null;
	
	//returns rowCount
	
	public int getRowCount(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return 0;
		else {
			sheet = workbook.getSheetAt(index);
			int number = sheet.getLastRowNum() + 1;
			return number;
		}

	}
	
	public int getRowByTestCaseName(String sheetName,String Testcasename) {
		int Rownumber=0;
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return 0;
		else {
			sheet = workbook.getSheetAt(index);
			//int number = sheet.getLastRowNum() + 1;
			for(int i=1;i<sheet.getLastRowNum()+1;i++) {
				//System.out.println("-"+sheet.getRow(i).getCell(1).getStringCellValue().trim());
				//System.out.println("--"+sheet.getRow(i).getCell(2).getStringCellValue().trim());
				if(sheet.getRow(i).getCell(1).getStringCellValue().trim().equals(Testcasename)) {
					Rownumber=i+1;	
					//System.out.println(Rownumber);
					break;
				}
				
			}
			return Rownumber;
		}

	}
	
	// returns the data from a cell
		public String getCellData(String sheetName, String colName, int rowNum) {
			try {
				if (rowNum <= 0)
					return "";

				int index = workbook.getSheetIndex(sheetName);
				int col_Num = -1;
				if (index == -1)
					return "";

				sheet = workbook.getSheetAt(index);
				row = sheet.getRow(0);
				for (int i = 0; i < row.getLastCellNum(); i++) {
					// System.out.println(row.getCell(i).getStringCellValue().trim());
					if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
						col_Num = i;
				}
				if (col_Num == -1)
					return "";

				sheet = workbook.getSheetAt(index);
				row = sheet.getRow(rowNum - 1);
				if (row == null)
					return "";
				cell = row.getCell(col_Num);

				if (cell == null)
					return "";

				// System.out.println(cell.getCellType().name());
				//
				if (cell.getCellType().name().equals("STRING"))
					return cell.getStringCellValue();

				// if (cell.getCellType().STRING != null)

				// if(cell.getCellType()==Xls_Reader.CELL_TYPE_STRING)
				// return cell.getStringCellValue();
				else if ((cell.getCellType().name().equals("NUMERIC")) || (cell.getCellType().name().equals("FORMULA"))) {

					String cellText = String.valueOf(cell.getNumericCellValue());
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						// format in form of M/D/YY
						double d = cell.getNumericCellValue();

						Calendar cal = Calendar.getInstance();
						cal.setTime(HSSFDateUtil.getJavaDate(d));
						cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
						cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + 1 + "/" + cellText;

						// System.out.println(cellText);

					}

					return cellText;
				} else if (cell.getCellType().BLANK != null)
					return "";
				else
					return String.valueOf(cell.getBooleanCellValue());

			} catch (Exception e) {

				e.printStackTrace();
				return "row " + rowNum + " or column " + colName + " does not exist in xls";
			}
		}

	private String toStringValue(Object object) {
		// handle NPE
		if(object==null){
			return "";
		}
		if(object instanceof Date){
			Date d = (Date) object;
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			return format.format(d);
		}
		if(object instanceof Double){
			double dblValue= (double) object;
			if(dblValue%1==0){
				return (int)dblValue+"";
			}
		}
		return object.toString();
	}
}