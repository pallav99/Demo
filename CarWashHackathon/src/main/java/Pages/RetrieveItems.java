package Pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import DriverSetup.DriverSetup;
import dataDriven.dataDriven;

public class RetrieveItems extends DriverSetup {
	public static dataDriven Excel = new dataDriven();
	By fitness = By.xpath("//*[@id=\"hotkeys_text_24\"]");
	By gym = By.xpath("//span[@title='Gym']");
	By opt = By.xpath("//span[@class='meditle1 lng_commn']");

	public void items() throws IOException {
		logger = report.createTest("Getting the submenu's");
		try {
			openURL("websiteURLKey");
			// ********to capture the screenshot********//
			
			driver.findElement(fitness).click();
			Screenshoot("Retrieve Submenu");
			driver.findElement(gym).click();
			
			// ********passing the report to excel********//
			reportPass("Gym option is clicked");
			List<WebElement> options = driver.findElements(opt);
			// ********writing data to excel********//
			Excel.writeData("Retrived GYM details"+ '\n');
			Screenshoot("Gym details retrived");
			for (WebElement option : options) {
				System.out.println(option.getText()+ '\n');
				Excel.writeData(option.getText()+ '\n');
			}
			reportPass("Sub menus are obtained Successfully.");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

}
