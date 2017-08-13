package LearningTestNG;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.Assert;

public class LearningTestNG1 {
	WebDriver driver;

	@BeforeSuite
	public void setDriver() {
		System.setProperty("webdriver.chrome.driver", "F:\\selenium-java-3.4.0\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@BeforeMethod
	public void launchWebsite() {
		driver.get("http://demosite.center/wordpress/wp-login.php");
		driver.findElement(By.id("user_login")).sendKeys("admin");
		driver.findElement(By.id("user_pass")).sendKeys("demo123");
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
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='wp-admin-bar-logout']/a")));
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
