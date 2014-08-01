import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import project.Admin;

public class AdminTest {
	private WebDriver	driver;
	private Admin		adm;
	
	@Before
	public void startDriver() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		adm = new Admin(driver);
	}

	@After
	public void stopDriver() {
		driver.close();
	}
	
	//@Test
	public void howToSlddider() throws InterruptedException {
		adm.open();
		adm.authAdmin();
		adm.adminSerf();
		adm.logout();
	}
	
	
}
