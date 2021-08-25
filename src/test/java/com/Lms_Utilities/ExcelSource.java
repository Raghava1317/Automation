package com.Lms_Utilities;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelSource {
	//Declaring object for XSSFWorkbook
	XSSFWorkbook wb;
	//Declaring object for sheet
	Sheet sheet;
	//Declaring the String variable for getting the values for excel.
	String data;

	//Creating the method for fetching the path of the excel file.
	public ExcelSource(String excelPath) throws Exception {
		File src = new File(excelPath);
		//FileInputStream is used to fetch the data from the excel file.
		FileInputStream fis = new FileInputStream(src);
		wb = new XSSFWorkbook(fis);
	}

	//Creating the method for fetching the values from the excel file.
	public String getData(int sheetNumber, int row, int column) {
		//Fetching the sheet number from excel file.
		sheet = wb.getSheetAt(sheetNumber);
		//Fetching the values from excel sheet based on the rows and columns.
		data = sheet.getRow(row).getCell(column).getStringCellValue();
		return data;
	}

}