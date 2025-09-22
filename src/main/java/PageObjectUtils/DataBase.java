package PageObjectUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataBase {

	String fileLocation = "D:\\Program Files\\DemoEcommerce\\src\\main\\java\\PageObject\\DemoBlazeData.xlsx";
public void ReadDataFromExcel(String sheetName ) throws IOException {
		
		// Establish the IO stream and set the file path
		FileInputStream  fis = new FileInputStream(fileLocation);
		// Open the workbook or set up the workbook object 
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		// workbook object getSheet() from the workbook
		XSSFSheet sheet = workbook.getSheet(sheetName);
		Iterator<Row> rows = sheet.iterator();
		while(rows.hasNext()) {
			Row row = rows.next();
			Iterator<Cell> cells = row.iterator();
			while (cells.hasNext()) {
				Cell cellValue = cells.next();
				String cellData = cellValue.getStringCellValue();
				System.out.print(cellData+" ");
			}
			System.out.println(" ");
		}
	}
	List<String> userInfo = new ArrayList<>();
	
	public List<String> readRowSpecificDataFromExcel(String sheetName, int rowNo) throws IOException {
		
		
		// Establish the IO stream and set the file path
		FileInputStream  fis = new FileInputStream(fileLocation);
		// Open the workbook or set up the workbook object 
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		// workbook object getSheet() from the workbook
		XSSFSheet sheet = workbook.getSheet(sheetName);
		//On a particular Row no
		XSSFRow row = sheet.getRow(rowNo);
			Iterator<Cell> cells = row.iterator();
			// Inner loop for iterating over cells
				while(cells.hasNext()) {
						Cell cellValue = cells.next();
						String cellData = cellValue.getStringCellValue();
						System.out.print(cellData+ "  ");
						userInfo.add(cellData);// From 0th index it will add the data, then 1, 2..
				}
			return userInfo;
		}
	
public void writeRegUsersToExcel(String Username, String password, String sheetName) throws IOException {
		
		// Establish the IO stream and set the file path
		FileInputStream  fis = new FileInputStream(fileLocation);
		// Open the workbook or set up the workbook object 
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		// workbook object getSheet() from the workbook
		XSSFSheet sheet = workbook.getSheet(sheetName);
		//To write the data find the last row and enter the data in the last row + 1th 
		int lastRow = sheet.getLastRowNum();
		//Create a new after the last row
		XSSFRow row = sheet.createRow(lastRow+1);
		//Create 2 cells to accommodate email and password data
		XSSFCell cell1 = row.createCell(0);
		XSSFCell cell2 = row.createCell(1);
		//Enter the value in to cells
		cell1.setCellValue(Username);
		cell2.setCellValue(password);
		//To write back to the excel, create the output stream 
		FileOutputStream fileOut = new FileOutputStream(fileLocation);
		workbook.write(fileOut);
		//After writing close the workbook & streams 
		workbook.close();
		fileOut.close();
		
	}
}
