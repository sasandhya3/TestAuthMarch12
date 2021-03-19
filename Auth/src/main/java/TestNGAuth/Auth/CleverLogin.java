package TestNGAuth.Auth;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileWriter;

public class CleverLogin {
	public void cleverCheck() throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		WebDriver driver = BaseTest.openBrowser();
		Properties obj = new Properties();
		FileInputStream objfile = new FileInputStream(System.getProperty("user.dir") + "\\app.properties");
		obj.load(objfile);
		FileWriter fw = new FileWriter("./results/clever_resultfile.txt");
		Select SSO = new Select(driver.findElement(By.xpath(obj.getProperty("ssoMethod"))));
		Select portal = new Select(driver.findElement(By.xpath(obj.getProperty("portal"))));
		WebDriverWait wait = new WebDriverWait(driver, 50);
		for (int i = 1; i < SSO.getOptions().size() - 1; i++) {
			SSO.selectByIndex(i);
			Select District = new Select(driver.findElement(By.xpath(obj.getProperty("district"))));
			for (int j = 1; j < District.getOptions().size(); j++) {
				District.selectByIndex(j);
				fw.write("\n -------------------------------------------------------------");
				for (int k = 1; k < portal.getOptions().size(); k++) {
					portal.selectByIndex(k);
					WebElement name, dist;
					name = portal.getFirstSelectedOption();
					dist = District.getFirstSelectedOption();
					driver.findElement(By.xpath(obj.getProperty("login"))).click();
					String txt = name.getText();
					fw.write(txt);
					if (txt.equals("Staff")) { 
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ssoframe"));
						if (!(driver.findElements(By.xpath(obj.getProperty("launchPad"))).isEmpty())) {
							fw.write("***");
							if (!driver.findElements(By.xpath(obj.getProperty("role"))).isEmpty()) {
								fw.write(
										"Role= " + driver.findElement(By.xpath(obj.getProperty("roleName"))).getText());
								driver.switchTo().parentFrame();
							} else
								fw.write("\n" + "Login Success but Role is undefined");
							driver.switchTo().frame("ssoframe");
							driver.findElement(By.xpath(obj.getProperty("roleName"))).click();
							driver.findElement(By.xpath(obj.getProperty("logout"))).click();
							if (!driver.findElements(By.xpath(obj.getProperty("cleverLogout1"))).isEmpty()
									|| !driver.findElements(By.xpath(obj.getProperty("cleverLogout2"))).isEmpty()) {
								driver.switchTo().parentFrame();
								fw.write("\n" + SSO.getFirstSelectedOption().getText() + "***" + name.getText() + "***"
										+ dist.getText() + "***" + "Test pass");
							} else {
								driver.switchTo().parentFrame();
								fw.write("\n" + SSO.getFirstSelectedOption().getText() + "***" + name.getText() + "***"
										+ dist.getText() + "***" + "The logout screen is wrong");
							}
						} else {
							String txt1 = driver.findElement(By.xpath(obj.getProperty("errorMessage"))).getText();
							fw.write("\n" + "error message content ->" + txt1);
							driver.switchTo().parentFrame();
							fw.write("\n" + SSO.getFirstSelectedOption().getText() + "***" + name.getText() + "***"
									+ dist.getText() + "***" + "Error Login");
						}
					} else {
						driver.switchTo().frame("ssoframe");
						if (!driver.findElements(By.xpath(obj.getProperty("launchPad"))).isEmpty()) {
							driver.findElement(By.xpath(obj.getProperty("stdLogout"))).click();
							if (!driver.findElements(By.xpath(obj.getProperty("cleverLogout1"))).isEmpty()
									|| !driver.findElements(By.xpath(obj.getProperty("cleverLogout2"))).isEmpty()) {
								driver.switchTo().parentFrame();
								fw.write("\n" + SSO.getFirstSelectedOption().getText() + "***" + name.getText() + "***"
										+ dist.getText() + "***" + "Test pass");
							} else {
								driver.switchTo().parentFrame();
								fw.write("\n" + SSO.getFirstSelectedOption().getText() + "***" + name.getText() + "***"
										+ dist.getText() + "***" + "The logout screen is wrong");
								driver.switchTo().parentFrame();
							}
						} else {
							String txt1 = driver.findElement(By.xpath((obj.getProperty("errorMessage")))).getText();
							fw.write("\n" + "error message content" + txt1);
							driver.switchTo().parentFrame();
							fw.write("\n" + SSO.getFirstSelectedOption().getText() + "***" + name.getText() + "***"
									+ dist.getText() + "***" + "Error Login");
						}
					}
				}
			}
		}
		fw.close();
		driver.quit();
	}
}