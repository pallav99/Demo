package TestSuites;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import DriverSetup.DriverSetup;
import Pages.CarWash;
import Pages.FListing;
import Pages.RetrieveItems;

public class TestCases extends DriverSetup {
	// ********Declaration of varaibles********//
	CarWash cw = new CarWash();
	FListing fl = new FListing();
	RetrieveItems ri = new RetrieveItems();

//********Method invoking the browser on platform ********//
	@BeforeTest
	public void driverSetup() {
		logger = report.createTest("Executing Test Cases");

		cw.driverSetup();
		reportPass("Browser is Invoked");
		// ********Select 1 for chrome; select 2 for firefox********//
	}

	@Test(priority = 1)
	public void carwash() throws InterruptedException {
		// ********TO retrieve carwash********//

		cw.carwash();
		reportPass("Car washes are retrieved");
	}

	@Test(priority = 2)
	public void listing() throws IOException {
		fl.register();

		reportPass("Errors are obtained in free listing");
	}

	@Test(priority = 3)
	public void retrieve() throws InterruptedException, IOException {
		// ********To obtain sub menu list********//
		ri.items();
		reportPass("Sub Menus are Obtained");
	}

	@AfterTest
	// ********TO close the browser after the test********//
	public void closeBrowser() {
		ri.endReport();
		ri.closeBrowser();
	}

}
