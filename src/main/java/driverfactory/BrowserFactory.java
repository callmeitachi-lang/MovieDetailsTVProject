package driverfactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author knwrp
 *
 */
public class BrowserFactory {

	public WebDriver driver;
	Properties prop;

	/**
	 * 
	 * this method if used for initilizing driver
	 * 
	 * @param browserName
	 * @return driver
	 */
	public WebDriver init_Driver(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			driver = WebDriverManager.chromedriver().create();
			// new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = WebDriverManager.firefoxdriver().create();
			// driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = WebDriverManager.edgedriver().create();
			// driver = new EdgeDriver();
		} else {
			System.out.println("Please check the mentioned browser::" + browserName);
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		return driver;
	}

	/**
	 * this method is used for reading the configuration file
	 * 
	 * @return prop reference to get properties from config file
	 */
	public Properties init_prop() {

		FileInputStream ip;
		try {
			ip = new FileInputStream("./src/main/java/configurationsPackage/config.properties");
			prop = new Properties();
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

}
