package TestNGAuth.Auth;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ClassLogin {
	public void classCheck() throws InterruptedException, IOException {
		WebDriver driver = BaseTest.openBrowser();
		Properties obj = new Properties();
		FileInputStream objfile = new FileInputStream(System.getProperty("user.dir") + "\\app.properties");
		obj.load(objfile);
		FileWriter fw = new FileWriter("./results/class_resultfile.txt");
		Select SSO = new Select(driver.findElement(By.xpath(obj.getProperty("ssoMethod"))));
		Select portal = new Select(driver.findElement(By.xpath(obj.getProperty("portal"))));
		Select District = null;
		for (int i = 2; i < SSO.getOptions().size(); i++) {
			SSO.selectByIndex(i);
			District = new Select(driver.findElement(By.xpath(obj.getProperty("district"))));
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
					if (txt.equals("Staff")) {
						driver.switchTo().frame("ssoframe");
						if (!driver.findElements(By.xpath(obj.getProperty("launchPad"))).isEmpty()) {
							if (!driver.findElements(By.xpath(obj.getProperty("role"))).isEmpty()) {
								fw.write("\nRole= "
										+ driver.findElement(By.xpath(obj.getProperty("roleName"))).getText());
								driver.switchTo().parentFrame();
								fw.write("\n" + SSO.getFirstSelectedOption().getText() + "***" + name.getText() + "***"
										+ dist.getText() + "***" + "Test pass");
							} else {
								fw.write("\n Login Success but Role is undefined");
							}
						} else {
							String txt1 = driver.findElement(By.xpath(obj.getProperty("errorMessage"))).getText();
							fw.write("\n error message content ->" + txt1);
							driver.switchTo().frame("ssoframe");
						}
					} else {
						driver.switchTo().frame("ssoframe");
						if (!driver.findElements(By.xpath(obj.getProperty("launchPad"))).isEmpty()) {
							driver.switchTo().parentFrame();
							fw.write("\n" + SSO.getFirstSelectedOption().getText() + "***" + name.getText() + "***"
									+ dist.getText() + "***" + "Test pass");
						} else {
							String txt1 = driver.findElement(By.xpath((obj.getProperty("errorMessage")))).getText();
							fw.write("\n error message content" + txt1);
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
