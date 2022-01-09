package de.training.selenium;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class CrossBrowserTest {

	WebDriver driver;

	@BeforeTest
	@Parameters("browser")
	public void setup(String browser) throws Exception {
		try {
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "C://seleniumtraining//chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "C://seleniumtraining//geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("edge")) {
				System.setProperty("webdriver.edge.driver", "C://seleniumtraining//msedgedriver.exe");
				driver = new EdgeDriver();
			} else if (browser.equalsIgnoreCase("opera")) {
				System.setProperty("webdriver.opera.driver", "C://seleniumtraining//operadriver.exe");
				driver = new OperaDriver();
			} else {
				throw new Exception("Browser stimmt nicht");
			}
			System.out.println("Oeffnen: " + browser);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void test() throws InterruptedException {
		driver.get("https://www.google.com");

		WebElement sucheKisteElement = driver
				.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input"));
		sucheKisteElement.clear();
		sucheKisteElement.sendKeys("quality-stream Introduccion a la Automatizacion de Pruebas de Software");
		sucheKisteElement.submit();

		WebDriverWait warte = new WebDriverWait(driver, 7);
		warte.until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector("a[href='https://www.youtube.com/watch?v=R_hh3jAqn8M']")));
		assertTrue(driver.findElement(By.cssSelector("a[href='https://www.youtube.com/watch?v=R_hh3jAqn8M']"))
				.isDisplayed());
		driver.quit();
	}

}
