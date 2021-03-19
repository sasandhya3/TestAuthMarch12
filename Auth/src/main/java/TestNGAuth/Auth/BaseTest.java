package TestNGAuth.Auth;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest implements IAutoConst {
	public static WebDriver openBrowser() throws IOException {
		System.setProperty(KEY, VALUE); 
		WebDriver driver = new ChromeDriver();
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(TIME, TimeUnit.SECONDS);
		return driver;
	}
}
