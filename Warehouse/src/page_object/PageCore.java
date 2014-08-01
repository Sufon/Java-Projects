package page_object;

import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageCore  {
	//Base
	protected HashMap<String, WebElem> elem = new HashMap<String, WebElem>();
	protected String url = "http://warehouse.shulgin.dev.ok/manager";
	protected WebDriver driver;
	
	public PageCore (WebDriver _driver){
		driver = _driver;
	}

	protected void addElem(String _name, String _locator, String _locatorType) {
		elem.put(_name, new WebElem(driver, _name, _locator, _locatorType));
	}

	public void open() {
		driver.get(url);
	}
	
	public PageCore clickAndWait(String _name, int _time) throws InterruptedException {
		getWebElem(_name).clickAndWait(_time);
		return this;
	}
	
	public PageCore sendKyesAndWait(String _name,String _keys, int _time) throws InterruptedException {
		getWebElem(_name).sendKyesAndWait( _keys, _time);
		return this;
	}

	protected WebElement getElement(String name) {
		return elem.get(name).getElement();
	}

	protected List<WebElement> getWebElems(String name) {
		return elem.get(name).getList();
	}

	protected WebElem getWebElem(String name) {
		return elem.get(name);
	}
}
