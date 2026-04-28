package tutorialsninja;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class writeinexcel {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileOutputStream file = new FileOutputStream(System.getProperty("user.dir")+"\\testdata\\writefile.xlsx");
	
		XSSFWorkbook wb = new XSSFWorkbook();
		
		XSSFSheet sheet= wb.createSheet("Data");
		
		XSSFRow row1 = sheet.createRow(0);
			row1.createCell(0).setCellValue("Ankush");
			row1.createCell(1).setCellValue("Tiwari");
			row1.createCell(2).setCellValue(22);
		XSSFRow row2= sheet.createRow(1);
			row2.createCell(0).setCellValue("Ganesh");
			row2.createCell(1).setCellValue("Tiwari");
			row2.createCell(2).setCellValue(61);
			
			
		wb.write(file);
		wb.close();
	
		System.out.println("File is created");
	}

}
