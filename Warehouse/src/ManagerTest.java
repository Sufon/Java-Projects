import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import project.Manager;


public class ManagerTest {

	WebDriver driver;

	@Before
	public void startDriver() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@After
	public void stopDriver() {
		driver.close();
	}
	
	@Test
	public void howToSlddider() throws InterruptedException {

		Manager mngr = new Manager(driver);
		mngr.open();
		mngr.login();
		mngr.userWork("Manager");
		// mngr.editManager()

	}

}
