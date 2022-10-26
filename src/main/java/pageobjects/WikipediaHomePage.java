package pageobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Utilities.ElementUtil;
import driverfactory.BrowserFactory;

public class WikipediaHomePage extends BrowserFactory {

	private WebDriver driver;
	Properties prop;
	private ElementUtil elementutil;
	private By searchName = By.xpath("//input[@id='searchInput']");
	private By searchButton = By.cssSelector(".sprite.svg-search-icon");
	private By releaseDate = By
			.xpath("//div[@id='mw-content-text']/div/table/tbody//div[contains(text(),'Release date')]/../..//td");
	private By countryOrigin = By
			.xpath("//div[@id='mw-content-text']/div[1]/table[1]/tbody//th[text()=\"Country\"]/..//td");

	public WikipediaHomePage(WebDriver driver) {
		this.driver = driver;
		elementutil = new ElementUtil(driver);
	}

	public ArrayList<String> getMovieDetailsFromWikipedia(String movieName) {
		prop = init_prop();
		driver.get(prop.getProperty("wikiurl"));
		elementutil.doSendKeys(searchName, movieName);
		elementutil.doClick(searchButton);
		
		//String date = elementutil.doGetElementText(releaseDate);
		String date = elementutil.MoveToELementGetText(releaseDate);
		String values[] = date.split(" ");
		String releaseDate = values[1].concat(" " + values[0] + "," + " " + values[2]);
		//String countryOrigin = elementutil.doGetElementText(this.countryOrigin).trim();
		String countryOrigin = elementutil.MoveToELementGetText(this.countryOrigin).trim();
		return new ArrayList<String>(Arrays.asList(releaseDate, countryOrigin));
	}

}
