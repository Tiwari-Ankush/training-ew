package rediff.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.*;

public class ExcelUtil {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelUtil(String excelPath, String sheetName) {
        try (FileInputStream fis = new FileInputStream(excelPath)) {
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
        } catch (Exception e) {
            throw new RuntimeException("Excel file read failed: " + e.getMessage());
        }
    }

    public int getRowCount() {
        return sheet.getLastRowNum(); // last row index (0-based)
    }

    public int getCellCount(int rowNum) {
        return sheet.getRow(rowNum).getLastCellNum(); // count (not index)
    }

    public String getCellData(int rowNum, int colNum) {
        XSSFRow row = sheet.getRow(rowNum);
        XSSFCell cell = row.getCell(colNum);
        return (cell == null) ? "" : cell.toString();
    }

    public void close() {
        try {
            workbook.close();
        } catch (IOException e) {
            // ignore
        }
    }
}