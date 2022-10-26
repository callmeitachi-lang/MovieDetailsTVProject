package pageobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utilities.ElementUtil;
import driverfactory.BrowserFactory;

public class ImdbHomePage extends BrowserFactory {

	private WebDriver driver;
	private Properties prop;
	private ElementUtil elementutil;
	private By searhMovieName = By.xpath("//input[@id='suggestion-search']");
	private By movieList = By.xpath("//div[@class='sc-d2740ffb-2 STkQo searchResult__constTitle']");
	private By releaseDate = By.xpath("//li[@data-testid='title-details-releasedate']//ul[@role='presentation']");
	private By countryOrigin = By.xpath("//span[normalize-space()='Country of origin']//parent::li//div/ul/li/a");

	public ImdbHomePage(WebDriver driver) {
		this.driver = driver;
		elementutil = new ElementUtil(driver);
	}

	/**
	 * Method created to get the movie release date and country of origin
	 * 
	 * @param movieName
	 * @return release date,Country Origin
	 */
	public ArrayList<String> getMovieDetailsFromImdb(String movieName) {
		prop = init_prop();
		driver.get(prop.getProperty("imdburl"));
		elementutil.doSendKeys(searhMovieName, movieName);
		elementutil.doActionsSendKeys(movieList, movieName);
		//elementutil.clickOnElementFromSection(movieList, movieName);
		
		//String releaseDate = elementutil.doGetElementText(this.releaseDate).substring(0, 17);		
		String releaseDate = elementutil.MoveToELementGetText(this.releaseDate).substring(0, 17);		
		//String countryOrigin = elementutil.doGetElementText(this.countryOrigin).trim();
		String countryOrigin = elementutil.MoveToELementGetText(this.countryOrigin).trim();
		return new ArrayList<String>(Arrays.asList(releaseDate, countryOrigin));

	}

}
