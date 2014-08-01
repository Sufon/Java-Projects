package project;
import java.sql.Timestamp;
import static org.junit.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

import page_object.PageCore;
import page_object.WebElem;

public class Doctor extends PageCore {
	
	private ProjectSetting ps = new ProjectSetting();
	
	public Doctor(WebDriver _driver) {
		super(_driver);
		setUrl(ps.url);
		waiteTime = 900;
		
		// Login
		addElem("login",			"login",				"name");
		addElem("pass",				"password",				"name");
		addElem("clickLogin",		".//*[@type='submit']");
		
		// Make an appointment
		addElem("calClick",			".//*[@class='date_filter_date left']//*[@class='ui-datepicker-trigger']");
		addElem("chooseDay",		".//*[@id='ui-datepicker-div']//*[text()='20']");
		addElem("dropStartlist",	".//*[@class='start_pop selecter ir_time_range_1']/*[@class='trig']");
		addElem("dStartTime",		".//*[@data-value='9:30']");
		addElem("dropEndlist",		".//*[@class='end_pop selecter ir_time_range_2']/*[@class='trig']");
		addElem("dEndTime",			".//*[@class='end_pop selecter ir_time_range_2 opened']//*[text()='12:30']");
		addElem("okComform",		".//*[@class='yellow big left save_rec']");
		
		//Select day in calendar
		addElem("selectDayCal",		"//*[@class='ui-datepicker-calendar']//*[text()='20']");
		
		//Delete appointment from calendar
		addElem("patClicCal",		".//*[text()='9:30-12:30']/following-sibling::td[1]");
		addElem("delPatFCal",		".//*[text()='9:30-12:30']/following-sibling::td[1]//*[@class='del_rec']");
		
		//Patient List
		addElem("patList",			".//*[@class = 'active']");

		//Watch patient card and create new note
		addElem("open1User",		".//*[@class = 'blog']//*[text()='Вулис Илья Давидович']");
		addElem("openNote",			"(.//*[@class='blog_cont records']//*[@class='data_s c_hover'])[1]");
		addElem("addNote",			".//*[@class='blue big left patiend_add_record']");
		addElem("writeNote",		".//*[@class='editorinput']");
		addElem("saveNote",			".//*[@class = 'yellow big left save_pat']");
		
		//Edit some note
		addElem("openNoteWithID",	"(.//*[@class='blog_cont records']//*[@class='data_s c_hover'])[1]");
		addElem("clickEditNote",	".//*[@class='blue big left patiend_chng_record']");
		addElem("chooseDay2",		".//*[@id='ui-datepicker-div']//*[text()='14']");

		//Week Calendar
		addElem("openWeek",			".//*[text()='Неделя']");
		addElem("chooseDayInCalendar", ".//*[@id='ui-datepicker-div']//*[text()='1']");
		addElem("openCaln",			".//*[@class='ui-datepicker-trigger']");
	
		//Compare Patients
		addElem("openCompare",		".//*[text()='Сравнение']");
		addElem("click1Pat",		".//*[@id='patient-list-clone']//*[text()='Вулис Илья Давидович']");
		addElem("click2Pat",		".//*[@class='main right_main']//*[text()='Габовский Сергей Иванович']");
		
		
		//Compare Print info with Note
		addElem("clickPrintBtn", "//*[@class='yellow full template_print']");
		
		//Logout
		addElem("logout",			".//*[@class='logout']");
	}

	public void autorize() {
		sendKeysOnWebElem("login", "doc");
		sendKeysOnWebElem("pass", "doc");
		clickOnWebElem("clickLogin");
	}

	public void accToAssempt() throws InterruptedException {
		//Create new date to assempt patient
		Thread.sleep(waiteTime);
		JavascriptExecutor js = null;
		if (driver instanceof JavascriptExecutor) {
			js = (JavascriptExecutor) driver;
		}
		js.executeScript("$('#127 .button_element').children().first().click();");
		
		getEBN("calClick").click();
		Thread.sleep(waiteTime);
		getEBN("chooseDay").click();
		getEBN("dropStartlist").click();
		getEBN("dStartTime").click();
		getEBN("dropEndlist").click();
		getEBN("dEndTime").click();
		getWEBN("okComform").clickAndWait(waiteTime);
		getWEBN("selectDayCal").clickAndWait(waiteTime);
		getWEBN("patClicCal").clickAndWait(waiteTime);
		getWEBN("delPatFCal").clickAndWait(waiteTime);
		getEBN("patList").click();
	}

	public void watchNote() throws InterruptedException {
		// Open note
		getWEBN("patList").clickAndWait(waiteTime);
		getWEBN("open1User").clickAndWait(waiteTime);
		getWEBN("openNote").click();
	}

	public void addnote() throws InterruptedException {
		//Add note
		getWEBN("patList").clickAndWait(waiteTime);
		getEBN("open1User").click();
		int rowCount1 = driver.findElements(By.xpath(".//*[@id='patient-records']/div")).size();
		getWEBN("addNote").clickAndWait(waiteTime);
		getEBN("writeNote").sendKeys(" Новая запись" + new Timestamp(System.currentTimeMillis()));
		Thread.sleep(waiteTime);
		getEBN("saveNote").click();
		getEBN("patList").click();
		getEBN("open1User").click();
		int rowCount2 = driver.findElements(By.xpath(".//*[@id='patient-records']/div")).size();
		if (rowCount2 - rowCount1 != 1) {
			fail("Новая запись не создалась");
		}
	}

	public void editNote() throws InterruptedException {
		//Edit note
		getWEBN("patList").clickAndWait(waiteTime);
		getWEBN("open1User").clickAndWait(waiteTime);
		getEBN("openNoteWithID").click();

		// Test edit note
		WebElement element = driver.findElement(By.xpath(".//*[@class='editorinput']"));
		String text1 = element.getText();
		Thread.sleep(waiteTime);
		getWEBN("clickEditNote").clickAndWait(waiteTime);
		getEBN("writeNote").sendKeys(" " + new Timestamp(System.currentTimeMillis()));
		Thread.sleep(waiteTime);
		getEBN("saveNote").click();
		getEBN("patList").click();
		getWEBN("open1User").clickAndWait(waiteTime);
		WebElement element2 = driver.findElement(By.xpath(".//*[@id='67']/div[3]"));
		String text2 = element2.getText();
		if (text1.equals(text2)) {
			fail("Новая запись не отредактировалась");
		}

		// Update after test
		getWEBN("openNote").clickAndWait(waiteTime);
		getWEBN("clickEditNote").clickAndWait(waiteTime);
		getEBN("writeNote").clear();
		getEBN("writeNote").sendKeys("Updated " + new Timestamp(System.currentTimeMillis()));
		Thread.sleep(waiteTime);
		getEBN("saveNote").click();
		getEBN("patList").click();
		getEBN("open1User").click();
	}
		
	public void weekCalendar() throws InterruptedException{
		//Work with week calendar
		getEBN("openWeek").click();
		Thread.sleep(waiteTime);
		WebElement element1 = driver.findElement(By.xpath(".//*[@id='ir_week']/tbody/tr[1]/th[2]/span"));
		String date1 = element1.getText();
		//System.out.println(date1);
		getEBN("openCaln").click();
		Thread.sleep(waiteTime);
		getEBN("chooseDayInCalendar").click();
		Thread.sleep(waiteTime);
		WebElement element2 = driver.findElement(By.xpath(".//*[@id='ir_week']/tbody/tr[1]/th[2]/span"));
		String date2 = element2.getText();
		if (date1.equals(date2)) {
			fail("Дата не изменилась");
		}
		//getEBN("openWeek").click();	
	}

	public void comparePatient() throws InterruptedException{

		//Compare patients
		getWEBN("openCompare").clickAndWait(waiteTime);
		WebElement element1 = driver.findElement(By.xpath(".//*[@id='patient-list-clone']//*[text()='Вулис Илья Давидович']"));
		String nameA1 = element1.getText();
		WebElement element2 = driver.findElement(By.xpath(".//*[@class='main right_main']//*[text()='Габовский Сергей Иванович']"));
		String nameB1 = element2.getText();	
		getEBN("click1Pat").click();
		getWEBN("click2Pat").clickAndWait(waiteTime);
		WebElement element3 = driver.findElement(By.xpath(".//*[text()='Вулис Илья Давидович']"));
		String nameA2 = element3.getText();
		WebElement element4 = driver.findElement(By.xpath(".//*[text()='Габовский Сергей Иванович']"));
		String nameB2 = element4.getText();
		if (!nameA1.equals(nameA2) && !nameB1.equals(nameB2)) {
			fail("Имена изменились");
		}
//		getEBN("click2Pat").click();
	}
	
	public void comparePrintResult() throws InterruptedException{
		
		getWEBN("patList").clickAndWait(waiteTime);
		getWEBN("open1User").clickAndWait(waiteTime);
		
		WebElement element5 = driver.findElement(By.xpath(" //*[@class='value_u pat_birth']"));
		String birth = element5.getText();	
		WebElement element6 = driver.findElement(By.xpath("//*[@class='value_u pat_age']"));
		String age = element6.getText();
		WebElement element7 = driver.findElement(By.xpath(" //*[@class='value_u pat_blood']"));
		String blood = element7.getText();
		
		
		getWEBN("openNoteWithID").clickAndWait(1000);
		WebElement element = driver.findElement(By.xpath(".//*[@class='editorinput']"));
		String text1 = element.getText();
		
		getWEBN("clickPrintBtn").clickAndWait(waiteTime);
		
		WebElement element2 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/p[2]"));
		String text2 = element2.getText();
		if (!text1.equals(text2)) {
			fail("не сошлось ");
		}

		System.out.println(age);
		System.out.println(blood);
		System.out.println(birth);
		  if(!driver.findElement(By.xpath("/html/body/div[3]/div[1]")).getText().contains(age )){
			  fail("не нашлось Возроста: "+ age);
		   }
		   else if(!driver.findElement(By.xpath("/html/body/div[3]/div[1]")).getText().contains( blood )){
			   fail("не нашлось Группы крови: " + blood);
		   }
		   else if (!driver.findElement(By.xpath("/html/body/div[3]/div[1]")).getText().contains( birth )){
			   fail("не нашлось даты рождения: " + birth);
		   }
		   else {
			   System.out.println("Success");
		   }

			   
		
		
		/*
		  [@class='value_u pat_birth'] - дата рождения
		  [@class='value_u pat_age'] -возраст 
		  [@class='value_u pat_blood'] -blood 
		  [@class='left address']  -Пр.Довбуша 104б, Кв.153</span>
		  [@class="left phone"]     -+38 044 591 16 55</span>
		  [@class="left email"]     -piy41wln@gmail.com</span> 
		  [@class="left work"]       ->+38045 997 50 37</span> 
		  [@class="left mobile"]      ->+38 078 232 22 86</span>
		 */
		 

		
	}
	
	public void logout() {
		getEBN("logout").click();
	}
}
