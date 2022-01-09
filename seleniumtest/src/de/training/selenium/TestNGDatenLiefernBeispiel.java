package de.training.selenium;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class TestNGDatenLiefernBeispiel {

	WebDriver driver;

	By anmeldenLokator = By
			.xpath("/html/body/div/div/div[2]/div[2]/div/div/div[2]/div[3]/div/div/div/div[1]/form/div[4]/a");
	By emailLokator = By.id("passp-field-login");
	By anmeldenKnopfLokator = By.id("passp:sign-in");

	By passwortLokator = By
			.xpath("/html/body/div/div/div[2]/div[2]/div/div/div[2]/div[3]/div/div/div/form/div[2]/span/input");
	By abmeldenKnopfLokator = By.xpath("/html/body/div[2]/div[2]/div[8]/div/div[2]/div/div/div[3]/div/div/a[1]");
	By abmeldenKnopfLokator02 = By
			.xpath("/html/body/div[2]/div[2]/div[8]/div/div[2]/div/div/div[3]/div/div/div/ul/ul/li[6]/a");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "C://seleniumtraining//chromedriver.exe");
		ChromeOptions chromeOptionen = new ChromeOptions();
		chromeOptionen.addArguments("--disable-notifications");// QUITARIA NOTIFICACIONES
		driver = new ChromeDriver(chromeOptionen);
		// driver.manage().window().maximize();
		driver.get(
				"https://passport.yandex.com/auth?from=mail&origin=hostroot_homer_auth_com&retpath=https%3A%2F%2Fmail.yandex.com%2F&backpath=https%3A%2F%2Fmail.yandex.com%3Fnoretpath%3D1");
	}

	@DataProvider(name = "datenAuthentifizierung")
	public Object[][] datenBekommen() {
		Object[][] daten = new Object[1][2]; // [1][2], [1] una fila y [2] dos columnas, [0][0] email y [0][1]
												// contrasena. Para dos
												// usuarios seria [2][2], [1][0] para siguiente email y [1][1] para
												// siguiente
												// contrasena
		daten[0][0] = "joseg1037@yandex.com";
		daten[0][1] = "Qwertz987.,!";

		return daten;
	}

	@Test(dataProvider = "datenAuthentifizierung")
	public void anmelden(String email, String passwort) {
		try {
			if (driver.findElement(anmeldenLokator).isDisplayed()) {
				WebDriverWait warte = new WebDriverWait(driver, 10);
				warte.until(ExpectedConditions.presenceOfElementLocated(emailLokator));

				driver.findElement(emailLokator).sendKeys(email);
				driver.findElement(anmeldenKnopfLokator).click();

				TimeUnit.SECONDS.sleep(3);

				driver.findElement(passwortLokator).sendKeys(passwort);
				driver.findElement(anmeldenKnopfLokator).click();

				TimeUnit.SECONDS.sleep(10);

				driver.findElement(abmeldenKnopfLokator).click();
				assertEquals(driver.findElement(abmeldenKnopfLokator02).getText(), "Log out");
				driver.findElement(abmeldenKnopfLokator02).click();
			} else {
				System.out.println("Zeichen fuers Anmelden steht nicht auf der Webseite");
			}
		} catch (Exception ex) {
			System.out.println("Fehler: " + ex.getMessage());
		}
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
