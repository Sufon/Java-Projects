package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class WebElem {
	WebDriver driver;

	String name = " ";
	String locator = " ";
	String locatorType = " ";

	public WebElem(WebDriver _driver, String _name, String _locator,
			String _locatorType) {
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
		return null;
	}

	public List<WebElement> getList() {
		if (locatorType.equals("xpath"))
			return driver.findElements(By.xpath(locator));
		else if (locatorType.equals("name"))
			return driver.findElements(By.name(locator));
		return null;
	}

	public WebElem clickAndWait(int _time) throws InterruptedException {
		getElement().click();
		Thread.sleep(_time);
		return this;
	}

	public WebElem sendKyesAndWait(String _keys, int _time)
			throws InterruptedException {
		getElement().sendKeys(_keys);
		Thread.sleep(_time);
		return this;
	}
}
