package de.training.selenium.berichtsbeispiel;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class SucheChiffonkleider {
	WebDriver driver;
	By suchfeldLokator = By.id("search_query_top");
	By ergebnisseLokator = By.cssSelector("span.heading-counter");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "C://seleniumtraining//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php");
	}

	@Test
	public void sucheChiffonkleider() {
		WebElement suchfeld = driver.findElement(suchfeldLokator);
		suchfeld.clear();
		suchfeld.sendKeys("chiffon dresses");
		suchfeld.submit();

		WebDriverWait warte = new WebDriverWait(driver, 7);
		warte.until(ExpectedConditions.presenceOfElementLocated(ergebnisseLokator));

		System.out.println("Ergebniszahl: " + driver.findElement(ergebnisseLokator).getText());

		assertEquals(driver.findElement(ergebnisseLokator).getText(), "2 results have been found.");
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
