package test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import driverfactory.BrowserFactory;
import pageobjects.ImdbHomePage;
import pageobjects.WikipediaHomePage;

public class BaseTest {
	BrowserFactory browserfactory;
	WebDriver driver;
	Properties prop;
	ImdbHomePage imdbhomepage;
	WikipediaHomePage wikipediahomepage;

	@BeforeTest
	public void setup() {
		browserfactory = new BrowserFactory();
		prop = browserfactory.init_prop();

		driver = browserfactory.init_Driver(prop.getProperty("browser"));
		imdbhomepage = new ImdbHomePage(driver);
		wikipediahomepage=new WikipediaHomePage(driver);

	}

	/*
	 * @AfterTest public void tearDown() { driver.quit(); }
	 */

}
