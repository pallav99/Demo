package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import DriverSetup.DriverSetup;
import dataDriven.dataDriven;

public class CarWash extends DriverSetup {

	public static dataDriven Excel = new dataDriven();

	

	//By city = By.id("city");
    By choice = By.id("Hyderabad");
    By care = By.id("hotkeys_text_5");
    By wash = By.xpath("//span[text()='Car Wash']");
    By Ratings = By.xpath("//span[@class='green-box']");
    By Names = By.xpath("//span[@class='lng_cont_name']");
    By cont = By.xpath("//p[@class='contact-info ']"); 
    By vote = By.xpath("//p/a/span[@class='rt_count lng_vote']");
    By rate =By.className("lng_srtfltr");

	public void carwash() throws InterruptedException {
		
		// ********to get the car wash above 4.0 rating********//
		logger = report.createTest("Getting Car Washes greater than 4.0 rating.");
        try {
        openURL("websiteURLKey");
        Screenshoot("Homepage");
        WebElement city = driver.findElement(By.id("city"));
        city.click();
        HighlightElement.highlightElement(driver, city);
        driver.findElement(choice).click();
        driver.findElement(care).click();
        Screenshoot("Autocare");
        Thread.sleep(3000);
        driver.findElement(wash).click();
        Screenshoot("CarWash services");
        driver.findElement(rate).click();
        reportPass("Car wash Page is clicked");
        List<WebElement> ratings = driver.findElements(Ratings);
        List<WebElement> names = driver.findElements(Names);
        List<WebElement> contacts = driver.findElements(cont);
        
        
        System.out.println("Retrived CarWash details above 4 rating"+ '\n');
        Excel.writeData("Retrived CarWash details above 4 rating"+ '\n');
       
        for (int i = 0; i < 6; i++) {
            float rate = Float.parseFloat(ratings.get(i).getText());
            if (rate > 4) {
                System.out.println(ratings.get(i).getText() + " - "
                        + names.get(i).getText()+ " - "
                                + contacts.get(i).getText());
                reportPass("Car Washes name and ratings are obtained.");
                
                Excel.writeData(ratings.get(i).getText() + " - "
                        + names.get(i).getText()+ " - "
                        + contacts.get(i).getText() + '\n');
            }
            
        }
        } catch (Exception e) {
            reportFail(e.getMessage());
        }
    }

 

}