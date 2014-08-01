package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebElem {
	private WebDriver		driver;

	private String			name = "";
	private String			locator = "";
	private String			locatorType = "";

	public WebElem(WebDriver _driver, String _name, String _locator, String _locatorType) {
		name = _name;
		locator = _locator;
		locatorType = _locatorType;
		driver = _driver;
	}

	public WebElement getElement() {
		if (locatorType.equals("xpath"))
			return driver.findElement(By.xpath(locator));
		else if (locatorType.equals("name"))
			return driver.findElement(By.name(locator));
		else if (locatorType.equals("className"))
			return driver.findElement(By.className(locator));
		else if (locatorType.equals("cssSelector"))
			return driver.findElement(By.cssSelector(locator));
		else if (locatorType.equals("id"))
			return driver.findElement(By.id(locator));
		else if (locatorType.equals("linkText"))
			return driver.findElement(By.linkText(locator));
		else if (locatorType.equals("partialLinkText"))
			return driver.findElement(By.partialLinkText(locator));
		else if (locatorType.equals("tagName"))
			return driver.findElement(By.tagName(locator));
		return null;
	}
	
	public WebElem click() {
		log("Click on " + name + " [" + locator + "]");
		getElement().click();
		return this;
	}

	public WebElem sendKeys(String _keys) {
		log("Send Keys on " + name + " [" + locator + "]");
		getElement().sendKeys(_keys);
		return this;
	}
	
	public WebElem clickAndWait(int _time) throws InterruptedException {
		log("Click on " + name + " and waite " + _time + " millis" + " [" + locator + "]");
		getElement().click();
		Thread.sleep(_time);
		return this;
	}
	
	public WebElem sendKyesAndWait(String _keys, int _time) throws InterruptedException {
		log("Send Keys on " + name + " and waite " + _time + " millis" + " [" + locator + "]");
		getElement().sendKeys(_keys);
		Thread.sleep(_time);
		return this;
	}
	
	private void log(String _message) {
		System.out.println(_message);
	}
}
