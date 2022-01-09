package de.training.linkstesten;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class LinksCheckenTest {

	WebDriver driver;
	LinksCheckenSeite seite;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "C:\\seleniumtraining\\chromedriver.exe");
		driver = new ChromeDriver();
		seite = new LinksCheckenSeite(driver);
		driver.get("http://www.mercury-tours.com/");
	}

	@Test
	public void linksChecken() {
		assertTrue(seite.checkenSeiteLinks(), "Es gibt ungueltige Links");
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
