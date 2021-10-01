package DriverSetup;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ExtentReportManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.common.io.Files;

public class DriverSetup {

	public static WebDriver driver;
	public static Properties prop;
	JavascriptExecutor js;
	public static WebDriverWait wait;
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public ExtentTest logger;

	// ********To call different browsers********//
	public void driverSetup() {
		// *****Accept the input from user for which browser to launch*****//
		System.out.println("Select the browser number:  1.Google Chrome  2.Microsoft Edge");
		

		int choice = 1;
		if (choice == 1) {
			System.out.println("Google Chrome");
			driver = DriverSetup.getChromeBrowser();

			// ********To maximize the Browser Window********//
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		}

		else if (choice == 2) {
			System.out.println("Microsoft Edge");
			driver = DriverSetup.getEdgeBrowser();
		} else {
			System.out.println("Enter a valid choice (1 or 2 )");

		}

	}

	//******** To open the Main Page URL********//
	public void openURL(String websiteURLKey) {
		prop = new Properties();

		try {
			prop.load(new FileInputStream("src/main/java/Config/config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver.get(prop.getProperty(websiteURLKey));
	}

// ********Code To invoke Google Chrome ********//
	public static WebDriver getChromeBrowser() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\1) final\\IdentifyCarWash\\IdentifyCarWashLast\\IdentifyCarWashLast\\IdentifyCarWash\\drivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("--disable-blink-features=AutomationControlled");
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		return driver;
	}

// ********Code to invoke Microsoft Edge********//
	public static WebDriver getEdgeBrowser() {
		System.setProperty("webdriver.edge.driver",
				"D:\\1) final\\IdentifyCarWash\\IdentifyCarWashLast\\IdentifyCarWashLast\\IdentifyCarWash\\drivers\\msedgedriver.exe");
		driver = new EdgeDriver();

		return driver;
	}

	// ********Function to show the failed test cases in the report********//
	public void reportFail(String report) {
		logger.log(Status.FAIL, report);
	}

	// ********Function to show the passed test cases in the report********//
	public void reportPass(String report) {
		logger.log(Status.PASS, report);
	}

	// ********Function to take Screenshot of screen********//
	public void Screenshoot(String fileName) throws IOException {
		TakesScreenshot capture = (TakesScreenshot) driver;
		File srcFile = capture.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir") + "/Screenshot/" + fileName + ".png");
		Files.copy(srcFile, destFile);
	}

	// ********To input all data to the report********//
	public void endReport() {
		report.flush();
	}

	// ********To close the Browser********//
	public void closeBrowser() {
		driver.quit();
	}

}
