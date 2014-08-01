package project;
import static org.junit.Assert.fail;

import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import page_object.PageCore;


public class Manager extends PageCore {


	public Manager(WebDriver _driver) {
		super(_driver);
		//Login
		
		addElem("writeLoginA","//*[@id='login']" , "xpath" );
		addElem("writePassA","//*[@id='password']" , "xpath" );
		addElem("authButtonA","//*[@id='auth']/div/input" , "xpath" );
		
		//Manager
		addElem("openListManager", "//*[@id='nav-left-manager']/a[5]/li", "xpath");

		//Admin
		addElem("openListAdmin", "//*[@id='nav-left-manager']/a[7]/li", "xpath");
		
		//Keepers
		addElem("openListKeeper", "//*[@id='nav-left-manager']/a[6]/li", "xpath");
	
		//General
		addElem("addManager", "//*[@id='func-buttons']/div/a", "xpath");
		addElem("writeName", "//*[@data-rv-content='user.firstname']", "xpath");
		addElem("writeLogin", "//*[@data-rv-content='user.login']",  "xpath");
		addElem("writePass", "//*[@data-rv-content='user.password']", "xpath");
		addElem("writeComment", "//*[@data-rv-content='user.comment']", "xpath");
		addElem("save", "//*[@id='func-buttons']/div/a", "xpath");
		addElem("clickDel", "//*[@id='func-buttons']/div[4]/a", "xpath");
		addElem("confirmDel", "//*[@id='ir_conf_remove']", "xpath");
		addElem("lastPage","//*[@id='ir_paging']//*[@title='Go to last page']","xpath");
		addElem("getSizeOfList", ".//*[@id='view_content']/div/table/tbody/tr", "xpath");
		
		

		//addElem("opengGroup", ".//*[@id='1']/a", "xpath");
		//addElem("opengGroup", ".//*[@id='1']/a", "xpath");
		//addElem("opengGroup", ".//*[@id='1']/a", "xpath");
		//addElem("opengGroup", ".//*[@id='1']/a", "xpath");
		//addElem("opengGroup", ".//*[@id='1']/a", "xpath");
		
		
		
	}
	public void login() throws InterruptedException{
		Thread.sleep(500);
		sendKyesAndWait("writeLoginA", "user1", 300);
		sendKyesAndWait("writePassA", "12345", 300);
		clickAndWait("authButtonA", 500);
	}

	public String generateRandomWord() {
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		String output = sb.toString();
		return output;
	}
	
	public void userWork(String _role) throws InterruptedException {
		
		clickAndWait("openList"+_role, 200); 
		//int rowCount1 = getWebElems("getSizeOfList").size();
		//System.out.println(rowCount1);
		clickAndWait("addManager", 200);
		
		String _name = generateRandomWord();
		String _login = generateRandomWord();
		String _pass = generateRandomWord();
		String _comment = generateRandomWord();
		
		sendKyesAndWait("writeName", _name, 300);
		sendKyesAndWait("writeLogin", _login, 300);
		sendKyesAndWait("writePass", _pass, 300);
		sendKyesAndWait("writeComment", _comment, 500);
		clickAndWait("save", 500);
		clickAndWait("openList"+ _role, 500);
		
		clickAndWait("lastPage", 500);	
		System.out.println(".//*[@id='view_content']//*[text()='" + _name + "']");
		try{
			driver.findElement(By.xpath(".//*[@id='view_content']//*[text()='" + _name + "']"));
			
		}
		catch(NoSuchElementException e){
			fail("Имя отсуствует");
		}
		//driver.findElement(By.xpath(".//*[@id='view_content']/div/table/tbody//*[text()='" + _name + "']")
		driver.findElement(By.xpath(".//*[@id='view_content']/div/table/tbody//*[text()='" + _name + "']")).click();
		//Test if field exist what we add
		System.out.println(_name + " " + _login + " " + _pass + " " + _comment);
		Assert.assertTrue(driver.findElement(By.xpath(".//*[@data-rv-content='user.firstname']")).getText().contains(_name));
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='single']/div[1]/div[3]/div[1]/p")).getText().contains(_pass));
		Assert.assertTrue(driver.findElement(By.xpath(".//*[@data-rv-content='user.login']")).getText().contains(_login));
		Assert.assertTrue(driver.findElement(By.xpath(".//*[@data-rv-content='user.comment']")).getText().contains(_comment));			
		clickAndWait("clickDel", 500);
		clickAndWait("confirmDel", 500);
	

	}
}
