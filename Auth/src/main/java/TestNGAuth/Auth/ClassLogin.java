package TestNGAuth.Auth;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClassLogin {

	public void classCheck() throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		WebDriver driver = HomePage.openBrowser();

		Properties obj = new Properties();
		FileInputStream objfile = new FileInputStream(System.getProperty("user.dir") + "\\app.properties");
		obj.load(objfile);

		Select SSO = new Select(driver.findElement(By.xpath(obj.getProperty("ssoMethod"))));
		Select portal = new Select(driver.findElement(By.xpath(obj.getProperty("portal"))));
		Select District = null;

		for (int i = 2; i < SSO.getOptions().size(); i++) {
			SSO.selectByIndex(i);

			District = new Select(driver.findElement(By.xpath(obj.getProperty("district"))));
			for (int j = 1; j < District.getOptions().size(); j++) {
				District.selectByIndex(j);

				for (int k = 1; k < portal.getOptions().size(); k++) {
					portal.selectByIndex(k);
					WebElement name, dist;
					name = portal.getFirstSelectedOption();
					dist = District.getFirstSelectedOption();
					driver.findElement(By.xpath(obj.getProperty("login"))).click();
					String txt = name.getText();
					Thread.sleep(50000);
					if (txt.equals("Staff")) {
						driver.switchTo().frame("ssoframe");
						if (!driver.findElements(By.xpath(obj.getProperty("launchPad"))).isEmpty()) {
							if (!driver.findElements(By.xpath(obj.getProperty("role"))).isEmpty()) {
								System.out.println(
										"Role= " + driver.findElement(By.xpath(obj.getProperty("roleName"))).getText());
								driver.switchTo().parentFrame();
								System.out.println(SSO.getFirstSelectedOption().getText() + "***" + name.getText()
										+ "***" + dist.getText() + "***" + "Test pass");
							} else {
								System.out.println("Login Success but Role is undefined");
							}
							/*
							 * driver.switchTo().frame("ssoframe");
							 * driver.findElement(By.xpath(obj.getProperty("classroleName"))).click();
							 * driver.findElement(By.xpath(obj.getProperty("logout"))).click();
							 */
						} else {

							String txt1 = driver.findElement(By.xpath(obj.getProperty("errorMessage"))).getText();
							System.out.println("error message content ->" + txt1);
							driver.switchTo().frame("ssoframe");
						}

					} else { // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='sortable-flip-panels']")));
						if (!driver.findElements(By.xpath(obj.getProperty("launchPad"))).isEmpty()) {

							Thread.sleep(10000);
							driver.switchTo().parentFrame();
							System.out.println(SSO.getFirstSelectedOption().getText() + "***" + name.getText() + "***"
									+ dist.getText() + "***" + "Test pass");
						} else {

							String txt1 = driver.findElement(By.xpath((obj.getProperty("errorMessage")))).getText();
							System.out.println("error message content" + txt1);

							driver.switchTo().parentFrame();
							System.out.println(SSO.getFirstSelectedOption().getText() + "***" + name.getText() + "***"
									+ dist.getText() + "***" + "Error Login");

						}
					}

				}

			}

		}
	}
}
