package tutorialsninja;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class readfromexcel {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\testdata\\apachePOIdemodata.xlsx");
		
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		
		//find no od rows and cells in particular sheet
		int totalRows = sheet.getLastRowNum();
		
		//how to capture row from sheet
		int totalCells = sheet.getRow(1).getLastCellNum();
		
		System.out.println("total rows "+totalRows);
		//row count from 0
		//cell count from 1
		System.out.println("total cells "+totalCells);
		
		for(int r=0; r<=totalRows;r++) {
			XSSFRow currRow =sheet.getRow(r);
			
			for(int c=0;c<totalCells;c++) {
				XSSFCell cell= currRow.getCell(c);
				System.out.print(cell.toString()+" ");
			}
			System.out.println();
		}
		
		wb.close();
		file.close();
	}

}
