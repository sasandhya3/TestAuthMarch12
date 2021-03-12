package TestNGAuth.Auth;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HomePage {
	public static WebDriver openBrowser() throws MalformedURLException {
		//WebDriver driver = new ChromeDriver();
		URL RemoteURL = new URL("http://localhost:4445/wd/hub");
		DesiredCapabilities capability =DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(RemoteURL,capability);
		driver.get("http://scsvc1.schoolcity.com/SSOverification/Common/Index");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
}
