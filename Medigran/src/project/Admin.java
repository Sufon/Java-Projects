package project;
import org.openqa.selenium.WebDriver;

import page_object.PageCore;


public class Admin extends PageCore {


	public Admin(WebDriver _driver) {
		super(_driver);
		ProjectSetting ps = new ProjectSetting();
		setUrl(ps.url);
		// Login
		addElem("login", "login", "name");
		addElem("pass", "password", "name");
		addElem("clickLogin", ".//*[@type='submit']", "xpath");
		addElem("groups", ".//*[text()='Группы']", "xpath");
		addElem("opengGroup", ".//*[@id='1']/a", "xpath");
		addElem("exitGroup", ".//*[@class='yellow small right cancel_grp_add']", "xpath");
		addElem("openSpec", ".//*[text()='Специальности']", "xpath");
		addElem("openDep", ".//*[text()='Отделения']", "xpath");
		addElem("openLogs", ".//*[text()='Логи']", "xpath");
		addElem("open1Log", ".//*[text()='department']", "xpath");
		addElem("openUserList", ".//*[text()='Пользователи']", "xpath");
		addElem("openg1User", ".//*[text()='Колтышева Елена Степановна']", "xpath");
		addElem("editUser", ".//*[@class='blue middle left change_patient']", "xpath");
		addElem("fillAfress", ".//*[@class='editorinput usr_address']", "xpath");
		addElem("saveUser", ".//*[@class='blue middle left add_patient']", "xpath");
		addElem("logout", ".//*[@class='logout']", "xpath");
		//addElem("opengGroup", ".//*[@id='1']/a", "xpath");
		//addElem("opengGroup", ".//*[@id='1']/a", "xpath");
		//addElem("opengGroup", ".//*[@id='1']/a", "xpath");		
	}

	public void authAdmin() {
		getEBN("login").sendKeys("todor");
		getEBN("pass").sendKeys("todor");
		getEBN("clickLogin").click();
	}

	public Admin clickAndWait(String _name, int _time) throws InterruptedException {
		getWEBN(_name).clickAndWait(500);
		return this;
	}
	
	public void adminSerf() throws InterruptedException {

		//getWEBN("groups").clickAndWait(500);
		clickAndWait("groups", 500);

		getWEBN("opengGroup").clickAndWait(500);
		getWEBN("exitGroup").clickAndWait(500);
		getWEBN("openSpec").clickAndWait(500);
		getEBN("openDep").click();
		getWEBN("openLogs").clickAndWait(500);
		getWEBN("open1Log").clickAndWait(500);
		getEBN("openUserList").click();
		getWEBN("openg1User").clickAndWait(500);
		Thread.sleep(500);
		getEBN("editUser").click();
		Thread.sleep(500);
		getWEBN("fillAfress").sendKyesAndWait("Some text", 500);
		getEBN("saveUser").click();
		// Delete info from addres field
		getEBN("openUserList").click();
		Thread.sleep(500);
		getEBN("openg1User").click();
		Thread.sleep(500);
		getEBN("editUser").click();
		Thread.sleep(500);
		getEBN("fillAfress").clear();
		getEBN("saveUser").click();
		getEBN("openUserList").click();
		// getEBN("groups").click();

	}

	public void logout() {
		getEBN("logout").click();
	}
}
