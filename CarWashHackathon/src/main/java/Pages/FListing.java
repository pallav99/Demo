package Pages;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import DriverSetup.DriverSetup;

public class FListing extends DriverSetup {

	By list = By.id("h_flist");
	By error = By.id("fcoe");

	public void register() throws IOException {
		logger = report.createTest("Trying to list in Free Listing");
		// ********passing invalid details********
		try {
			openURL("websiteURLKey");
			
			driver.findElement(list).click();
			
			reportPass("Free Listing page is Opened");
			FileInputStream fs = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/TestData.xlsx");
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet("Data");
			WebElement mobile = driver.findElement(By.id("fmb0"));
			mobile.sendKeys(sheet.getRow(1).getCell(0).getStringCellValue());
			// ********Highlighting the mobile element********//
			HighlightElement.highlightElement(driver, mobile);
			reportPass("Invalid data is entered in the Mobile number field");
			WebElement submit = driver.findElement(By.xpath("//*[@id=\"add_div0\"]/div[3]/button"));
			submit.click();
			// ********Highlighting the element submit********//
			HighlightElement.highlightElement(driver, submit);
			reportPass("Submit the data");
			System.out.println("***********************************************************" + '\n');
			System.out.println(driver.findElement(error).getText() + '\n');
			System.out.println("Invalid phone number" + '\n');
			Screenshoot("Error message");
			System.out.println("***********************************************************" + '\n');
			// ********passing the report********//
			reportPass("Error is obtained");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

}
