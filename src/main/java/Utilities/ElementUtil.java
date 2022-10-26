package Utilities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	private WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	public By getBy(String locatorType, String locatorValue) {
		By locator = null;
		switch (locatorType.toLowerCase()) {
		case "id":
			locator = By.id(locatorValue);
			break;
		case "name":
			locator = By.name(locatorValue);
			break;
		case "classname":
			locator = By.className(locatorValue);
			break;
		case "xpath":
			locator = By.xpath(locatorValue);
			break;
		case "cssselector":
			locator = By.cssSelector(locatorValue);
			break;
		case "linktext":
			locator = By.linkText(locatorValue);
			break;
		case "partiallinktext":
			locator = By.partialLinkText(locatorValue);
			break;
		case "tagname":
			locator = By.tagName(locatorValue);
			break;

		default:
			System.out.println("please pass the right locator....");
			break;
		}
		return locator;
	}

	public String MoveToELementGetText(By Locator)
	{
		 WebElement getValue= getElement(Locator);
		 Actions act=new Actions(driver);
		  
		 act.moveToElement(getValue).perform();
		 flash(getValue);
		 return getValue.getText();
		 
	}
	
	public void flash(WebElement element) {
		String bgcolor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 15; i++) {
			changeColor("rgb(0,200,0)", element);// 1
			changeColor(bgcolor, element);// 2
		}
	}

	private void changeColor(String color, WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
	}
	public WebElement getElement(By locator) {
		
		WebElement element= driver.findElement(locator);
         flash(element);
		return element;
	}

	
	
	
	public void doActionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}

	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public String doGetElementText(By locator) {
		return getElement(locator).getText();
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public String doGetAttributeValue(By locator, String attributeName) {
		return getElement(locator).getAttribute(attributeName);
	}

	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public void clickOnElementFromSection(By locator, String value) {
		List<WebElement> eleList = getElements(locator);
		System.out.println(eleList.size());

		for (WebElement e : eleList) {
			String text = e.getText();
			System.out.println("=================" + text + "==================");
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	public int getElementsListCount(By locator) {
		return getElements(locator).size();
	}

	public List<String> getElementsTextList(By locator) {
		List<String> eleTextList = new ArrayList<String>();
		List<WebElement> eleList = getElements(locator);
		for (WebElement e : eleList) {
			String text = e.getText();
			eleTextList.add(text);
		}
		return eleTextList;
	}

	public void printAllElementsText(By locator) {
		List<WebElement> eleList = getElements(locator);
		for (WebElement e : eleList) {
			String text = e.getText();
			System.out.println("===============" + text + "===============");
		}

	}

	// *****************************DropDownUtils************************//

	public void doSelectDropDownByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectDropDownByVisibleText(By locator, String visibleText) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(visibleText);
	}

	public void doSelectDropDownByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public int getSelectDropDownValueCount(By locator) {
		return getElements(locator).size();
	}

	public List<String> getSelectDropDownValuesList(By locator) {
		List<String> valuesList = new ArrayList<String>();
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();

		for (WebElement e : optionsList) {
			String text = e.getText();
			valuesList.add(text);
		}
		return valuesList;
	}

	public void selectValueFromSelectDropDown(By locator, String value) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();

		for (WebElement e : optionsList) {
			String text = e.getText();
			if (text.contains(value)) {
				e.click();
				break;
			}
		}
	}

	public void printSelectDropDownValues(By locator) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		System.out.println(optionsList.size());

		for (WebElement e : optionsList) {
			String text = e.getText();
			System.out.println("================" + text + "==============");
		}
	}

	public void selectValueFromDropDown(By locator, String value) {
		List<WebElement> countryOptions = getElements(locator);
		System.out.println(countryOptions.size());
		for (int i = 0; i < countryOptions.size(); i++) {
			String text = countryOptions.get(i).getText();
			System.out.println("============" + text + "============");
			if (text.contains(value)) {
				countryOptions.get(i).click();
				break;
			}
		}
	}

	// ******************************wait utils******************************//

	/**
	 * An expectation for checking that the title contains a case-sensitive
	 * substring
	 * 
	 * @param titleFraction
	 * @param timeOut
	 * @return
	 */
	public String waitForTitleContains(String titleFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
			return driver.getTitle();
		}
		return null;
	}


}
