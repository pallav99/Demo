package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HighlightElement {

	//******** to highlight the element********//
	public static void highlightElement(WebDriver driver, WebElement element) {

		// ********Implement JavaScriptExecutor********//
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].setAttribute('style','background:yellow; border:4px solid red;');", element);
		// ********implementing try and catch********//
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}

		jse.executeScript("arguments[0].setAttribute('style','border: solid 4px white');", element);
	}

}
