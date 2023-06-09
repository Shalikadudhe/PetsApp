package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
	public FileInputStream fis;
	public FileOutputStream fos;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;

	public XLUtility(String path) {
		this.path = path;
	}

	public int getRowCount(String sheetnm) throws IOException {

		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetnm);
		int rowcount = sheet.getLastRowNum();
		workbook.close();
		fis.close();

		return rowcount;
	}

	public int getCellCount(String sheetnm, int rownum) throws IOException {

		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetnm);
		row = sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();
		workbook.close();
		fis.close();

		return cellcount;
	}

	public String getCellData(String sheetnm, int rownum, int colnum) throws IOException {

		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetnm);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);

		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell);// return formatted value of cell as a string regardless
		} catch (Exception e) {
			data = "";
		}

		workbook.close();
		fis.close();
		return data;

	}

	public void setCellData(String sheetnm, int rownum, int colnum, String data) throws IOException {

		File xlfile = new File(path);

		if (!xlfile.exists()) { // If file not exists then create new file
			workbook = new XSSFWorkbook();

			fos = new FileOutputStream(path);
			workbook.write(fos);
		}

		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);

		if (workbook.getSheetIndex(sheetnm) == -1) // If sheet not exists then create new Sheet
			workbook.createSheet(sheetnm);
		sheet = workbook.getSheet(sheetnm);
		if (sheet.getRow(rownum) == null) // If row not exists then create new Row
			sheet.createRow(rownum);
		row = sheet.getRow(rownum);

		cell = row.createCell(colnum);
		cell.setCellValue(data);
		fos = new FileOutputStream(path);
		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();

	}

	public void fillGreenColor(String sheetnm, int rownum, int colnum) throws IOException {

		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetnm);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();
	}

	public void fillRedColor(String sheetnm, int rownum, int colnum) throws IOException {

		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetnm);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();
	}
}