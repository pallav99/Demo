package dataDriven;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {

	// Creating workbook instance that refers to .xlsx file
	static XSSFWorkbook wb;

	// Creating a sheet object to retrieve the object
	static XSSFSheet sheet;
	static int writeRow;

	public dataDriven() {

		// ***********Obtaining input bytes from a file**********//
		FileInputStream fis;
		try {
			File file = new File(System.getProperty("user.dir") + "\\Excel\\TestData.xlsx");
			fis = new FileInputStream(file);
			wb = new XSSFWorkbook(fis);
		} // catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getData(int row, int col) {
		// TODO Auto-generated method stub
		sheet = wb.getSheetAt(0);
		String data = sheet.getRow(row).getCell(col).getStringCellValue();
		return data;
	}

	public static void writeData(String data) {

		sheet = wb.getSheetAt(1);
		sheet.createRow(writeRow).createCell(0).setCellValue(data);
		try {
			FileOutputStream Fos = new FileOutputStream(System.getProperty("user.dir") + "\\Excel\\TestData.xlsx");
			wb.write(Fos);
			writeRow++;
			Fos.close();

		} catch (IOException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
