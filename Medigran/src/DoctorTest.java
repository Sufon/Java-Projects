 import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import project.Doctor;

public class DoctorTest {
	private WebDriver		driver;
	private Doctor			doc;

	@Before
	public void startDriver() {
		if(driver == null){
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		doc = new Doctor(driver);
	}

	@After
	public void stopDriver() {
		//driver.close();
	}

	//@Test
	public void howToSlider() throws InterruptedException {
		// Doctor workout
		doc.open();
		doc.autorize();
		doc.accToAssempt();
		doc.logout();
	}
	
	//@Test
	public void workWithPatients() throws InterruptedException {
		doc.open();
		doc.autorize();
		doc.watchNote();
		doc.addnote();
		doc.editNote();
		doc.logout();
	}
		
	//@Test
	public void weekCalendar() throws InterruptedException {
		// Doctor week calendar
		doc.open();
		doc.autorize();
		doc.weekCalendar();
		doc.logout();
	}
	
	//@Test
	public void comparePatients() throws InterruptedException {
		// Doctor week calendar
		doc.open();
		doc.autorize();
		doc.comparePatient();
		doc.logout();
	}
	
	@Test
	public void comparePrintInfo() throws InterruptedException {
		
		doc.open();
		doc.autorize();
		doc.comparePrintResult();
		
		
		
	}
}
