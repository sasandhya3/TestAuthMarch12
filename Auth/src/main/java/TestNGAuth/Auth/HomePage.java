package TestNGAuth.Auth;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HomePage {
	public static WebDriver openBrowser() {
		WebDriver driver = new ChromeDriver();
		driver.get("http://scsvc1.schoolcity.com/SSOverification/Common/Index");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
}
