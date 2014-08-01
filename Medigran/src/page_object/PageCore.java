package page_object;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageCore  {
	// Base
	protected WebDriver					driver;
	protected String					url;
	protected HashMap<String, WebElem>	elem = new HashMap<String, WebElem>();
	protected int						waiteTime = 0;
	
	public PageCore(WebDriver _driver){
		driver = _driver;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String _url) {
		url = _url;
	}

	protected void addElem(String _name, String _locator, String _locatorType) {
		elem.put(_name, new WebElem(driver, _name, _locator, _locatorType));
	}
	
	protected void addElem(String _name, String _locator) {
		elem.put(_name, new WebElem(driver, _name, _locator, "xpath"));
	}

	public void open() {
		driver.get(url);
	}
	
	protected WebElement getEBN(String name) {
		return elem.get(name).getElement();
	}
	
	protected WebElem getWEBN(String name) {
		return elem.get(name);
	}
	
	// Web elements actions
	public PageCore clickOnWebElem(String _name) {
		getWEBN(_name).click();
		return this;
	}
	
	public PageCore sendKeysOnWebElem(String _name, String _keys) {
		getWEBN(_name).sendKeys(_keys);
		return this;
	}
	
	public PageCore clickAndWaiteOnWebElem(String _name, int _time) throws InterruptedException {
		getWEBN(_name).clickAndWait(_time);
		return this;
	}
	
	public PageCore sendKyesAndWaitOnWebElem(String _name, String _keys, int _time) throws InterruptedException {
		getWEBN(_name).sendKyesAndWait(_keys, _time);
		return this;
	}
}
