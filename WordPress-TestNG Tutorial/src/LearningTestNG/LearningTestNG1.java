package learningTestNG;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.Assert;

public class LearningTestNG1 {
	WebDriver driver;
	LibraryClass lib = new LibraryClass();

	@BeforeSuite
	public void setDriver() {
		System.setProperty("webdriver.chrome.driver", "E:\\Purendra\\Selenium\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://demosite.center/wordpress/wp-login.php");
	}

	@BeforeMethod
	public void launchWebsite() {
		lib.externalTimeMethod(driver, 10, "//*[@id='user_login']");
		lib.ExcelPath("ExcelInputValues.xlsx");
		String username = lib.getValueFromExcel(0, 1, 0);
		String password = lib.getValueFromExcel(0, 1, 1);
		driver.findElement(By.id("user_login")).sendKeys(username);
		driver.findElement(By.id("user_pass")).sendKeys(password);
		driver.findElement(By.id("wp-submit")).click();
	}

	@Test(priority = 1)
	public void verifyUser() {
		String actualUsername = driver.findElement(By.xpath("//*[@id='wp-admin-bar-my-account']/a")).getText();
		String expectedUsername = "Howdy, admin";
		Assert.assertEquals(actualUsername, expectedUsername);
	}

	@Test(priority = 2)
	public void verifyPost() {
		driver.findElement(By.linkText("Posts")).click();
		driver.findElement(By.linkText("Add New")).click();
		String expectedText = "Selenium and TestNG Demo program tester28";
		driver.findElement(By.name("post_title")).sendKeys(expectedText);
		driver.findElement(By.id("publish")).click();
		driver.findElement(By.linkText("Posts")).click();
		List<WebElement> listofAllValues = driver
				.findElements(By.xpath("//*[@class = 'post-title page-title column-title']/strong/a"));
		WebElement actualTextElement = listofAllValues.get(0);
		String actualText = actualTextElement.getText().trim();
		Assert.assertEquals(actualText, expectedText);
	}

	@AfterMethod
	public void logOut() {
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(By.xpath("//*[@id='wp-admin-bar-my-account']"))).perform();
		lib.externalTimeMethod(driver, 5, "//*[@id='wp-admin-bar-logout']/a");
		driver.findElement(By.xpath("//*[@id='wp-admin-bar-logout']/a")).click();
	}

	@AfterTest
	public void closeWebsite() {
		driver.close();
	}

	@AfterSuite
	public void closeDriver() {
		driver.quit();
	}
}
