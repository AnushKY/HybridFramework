package com.mindtree.utility;

import java.util.HashMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	
	private static XSSFWorkbook excelWbook;
	private static XSSFSheet excelWSheet;
	private static XSSFRow row;
	private static XSSFCell cell;
	
	
	public static HashMap<String, String> getXLSXvalues(String xlsxFile, String sheetName, String uniqueRowID) throws Exception{
		
		boolean rowFound = false;
		int actRowID = 0;
		HashMap<String, String> datamap = new HashMap<String, String>();
		
		File excel = new File(xlsxFile);
		FileInputStream fis  = new FileInputStream(excel);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet ws = wb.getSheet(sheetName);
		XSSFCell cell;
		
		int rows = ws.getLastRowNum()+1;
		int cols = ws.getRow(0).getLastCellNum();
		
		
		for(int iRow=0; iRow<rows; iRow++) {
			XSSFRow row = ws.getRow(iRow+1);
			cell = row.getCell(0);
			if(cell!=null) {
				
				/*
				 * if(cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
				 * cell.setCellType(cell.CELL_TYPE_STRING); }
				 */
				 
				if(cell.getStringCellValue().equals(uniqueRowID)) {
					actRowID = iRow+1;
					rowFound = true;
					break;
				}
			}
		}
		
		if(rowFound) {
			for(int iCol=1; iCol<=cols;iCol++) {
				if(ws.getRow(0).getCell(iCol)!=null) {
					cell = ws.getRow(0).getCell(iCol);
					String columnName = cell.getStringCellValue();
					
					if(ws.getRow(actRowID).getCell(iCol)!=null) {
						cell = ws.getRow(actRowID).getCell(iCol);
						String columnValue = cell.getStringCellValue();
						datamap.put(columnName, columnValue);
					}else {
						datamap.put(columnName, "");
					}
				}
			}
		}else {
			throw new Exception("The specified row "+uniqueRowID+" was not found in the excelsheet");
		}
		return datamap;
	}
	
public static void writeXLSXvalues(String xlsxFile, String sheetName, String uniqueRowID,String columnName, String data) throws Exception{
		
		boolean rowFound = false;
		boolean colFound = false;
		int actRowID = 0;
		int actColID = 0;
		
		File excel = new File(xlsxFile);
		FileInputStream fis  = new FileInputStream(excel);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet ws = wb.getSheet(sheetName);
		XSSFCell cell;
		int rows = ws.getLastRowNum()+1;
		int cols = ws.getRow(0).getLastCellNum();
		
		
		for(int iRow=0; iRow<rows; iRow++) {
			XSSFRow row = ws.getRow(iRow+1);
			cell = row.getCell(0);
			if(cell!=null) {
				/*
				 * if(cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
				 * cell.setCellType(cell.CELL_TYPE_STRING); }
				 */
				if(cell.getStringCellValue().equals(uniqueRowID)) {
					actRowID = iRow+1;
					rowFound = true;
					break;
				}
			}
		}
		
		if(rowFound) {
			for(int iCol=1; iCol<=cols;iCol++) {
				if(ws.getRow(0).getCell(iCol)!=null) {
					cell = ws.getRow(0).getCell(iCol);
					String ExcelcolumnName = cell.getStringCellValue();
					if(ExcelcolumnName.equalsIgnoreCase(columnName)) {
						colFound = true;
						actColID = iCol;
						break;
					}
				}
			}
			if(colFound) {
				row = ws.getRow(actRowID);
				XSSFCell newCell = row.createCell(actColID);
				newCell.setCellValue(data);
				FileOutputStream fos = new FileOutputStream(excel);
				wb.write(fos);
				fos.flush();
				fos.close();
				
			}else{
				throw new Exception("The specified column "+columnName+" was not found in the excelsheet");
			}
		}else {
			throw new Exception("The specified row "+uniqueRowID+" was not found in the excelsheet");
		}
	}

	public static String getValueFromExcel(HashMap<String,String>value, String colName) {
		
		String sList = null;
		for(String item:value.keySet()) {
			if(item.equals(colName)) {
				sList  = value.get(item);
			}
		}
		return sList;
	}
	
	public static Object[][] getTableArray(String filePath, String sheetName) throws Exception{
		
		String[][] tabArray = null;
		
		try {
			FileInputStream excelFile = new FileInputStream(filePath);
			excelWbook = new XSSFWorkbook(excelFile);
			excelWSheet = excelWbook.getSheet(sheetName);
			int startRow = 1;
			int startCol = 1;
			int ci, cj;
			
			int totalRows = excelWSheet.getLastRowNum();
			
			int totalCols = 2;
			
			tabArray = new String[totalRows][totalCols];
			ci=0;
			for(int i = startRow; i<= totalRows;i++, ci++) {
				cj=0;
				for(int j=startCol;j<=totalCols;j++, cj++) {
					
					if(getCellData(i,j).equalsIgnoreCase("No")) {
						break;
					}else {
						System.out.println(getCellData(i,j));
						tabArray[ci][cj]=getCellData(i,j);
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//tabArray = removeNullValuesFromArray(tabArray);
		return(tabArray);
		
	}
	
	
	public static String getCellData(int rowNum, int colNum) throws Exception {

		cell = excelWSheet.getRow(rowNum).getCell(colNum);
		String cellData = cell.getStringCellValue();
		return cellData;


	}
	
	
}
