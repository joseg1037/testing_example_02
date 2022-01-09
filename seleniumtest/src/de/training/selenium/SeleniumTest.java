package de.training.selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SeleniumTest {
	WebDriver driver;

	public SeleniumTest() {
		System.setProperty("webdriver.chrome.driver", "C://seleniumtraining//chromedriver.exe");
		driver = new ChromeDriver();
		
		String url="http://localhost:8081/beispielanwendung01/";		
		driver.get(url); //MIT DEN PORTS 8081 (VOM APACHE TOMCAT DRIVER) DIE ANWENDUNG/DAS PROJEKT "BEISPIELANWENDUNG" VON ECLIPSE AUFRUFEN
		//driver.get("http://amazon.com");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //EIN DRIVER IN MAXIMAL 5 SEKUNDEN SCHLIESSEN, ODER WENIGER WENN ES MOEGLICH IST WEGEN DER GESCHWINDIGKEIT VOM COMPUTER
		assert driver.getTitle().equals("Beispielanwendung 01");
		assert driver.getCurrentUrl().equals(url);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SeleniumTest seleniumTest = new SeleniumTest();//ALLES SCHLIESSEN WAS IN DER KLASSE SELENIUM TEST UND CONSTRUCTOR IST
		
		seleniumTest.test_02_04();
		
		
		seleniumTest.tearDown();// TEAR DOWN METHODE AUFRUFEN
	}
	
	private void warte5Sekunden() {
		try {
			TimeUnit.SECONDS.sleep(5); //5 SEKUNDEN WARTEN
		}catch(InterruptedException ie) {
			System.out.println(ie.getMessage());
		}
	}
	
	private void tearDown() {	
		warte5Sekunden();
		driver.quit(); //NACH DEN 5 SEKUNDEN DEN DRIVER SCHLIESSEN
	}
	
	private void test_02_01() {
		warte5Sekunden();
		//Betatige den Link mit dem Linktext "Seite 1"
		driver.findElement(By.linkText("Seite 01")).click();
	}
	
	private void test_02_02() {			
		//Navigiere zur Seite 01
		warte5Sekunden();
		driver.navigate().to("http://localhost:8081/beispielanwendung01/seite1.html");
		
		//Navigiere wieder zuriuck zur Hauptseite
		warte5Sekunden();
		driver.navigate().back();
		
		//Navigiere wieder nach vorn
		warte5Sekunden();
		driver.navigate().forward();
		
		//Ueberpruefe ob die aktuelle Webseite die Seite 01 ist.
		assert driver.getCurrentUrl().equals("http://localhost:8081/beispielanwendung01/seite1.html");
	}
	
	private void test_02_03() {
		//Navigiere zur Seite 02
		warte5Sekunden();
		driver.navigate().to("http://localhost:8081/beispielanwendung01/seite2.html");
		
		//Finde das Webelement mit der Id "benutzer_01"
		driver.findElement(By.id("benutzer_01"));
		
		//Besorge alle WebElemente mit dem Klassennamen "mann"
		List<WebElement> webElementeMaennlicheBenutzer=driver.findElements(By.className("mann"));
		assert webElementeMaennlicheBenutzer.size()==3;
		
		webElementeMaennlicheBenutzer.stream().forEach(n->System.out.println(n.getText()));
		
	}
	
	private void test_02_04() {
		//Navigiere zur Seite 03
		warte5Sekunden();
		driver.navigate().to("http://localhost:8081/beispielanwendung01/seite3.html");
		
		//Setze den Trainernamen auf Max Mustermann..
		driver.findElement(By.id("trainername")).sendKeys("Max Mustermann");
		
		//Selektiere die Verfuegbarkeit "ja"
		driver.findElement(By.id("ja")).click();
		
		//Selektiere die Kenntnisse "java" und "c"
		driver.findElement(By.id("java")).click();
		driver.findElement(By.id("c")).click();
		
		//Ueberpruefen ob der Trainer min. eine Programmiersprache kennt
		List <WebElement> kenntnisseWebElement=driver.findElements(By.name("kenntnisse"));
		assert kenntnisseWebElement.stream().filter(n->n.isSelected()).count()>0;
	}
	private void test_02_05() {
		//Navigiere zur Seite 03
		warte5Sekunden();
		driver.navigate().to("http://localhost:8081/beispielanwendung01/seite3.html");;
		//Selektiere die Wochentage "Montag", "Dienstag" un "Mittwoch" von einer Liste von Combobox
		Select wochentageSelect=new Select(driver.findElement(By.id("wochentage")));
		wochentageSelect.selectByIndex(0);//Montag, die Liste faengt mit Null an, also die Zahl oder position von der "option" Tag minus eins
		wochentageSelect.selectByValue("dienstag"); //Name vom Merkmal "value" vom Element und Tag "option"
		wochentageSelect.selectByVisibleText("Mittwoch"); //Text vom Element und Tag "option" von der Liste 
		
		//Ueberpruefe, ob mehr als ein Wochentag augwaehlt wurde
		assert !wochentageSelect.getAllSelectedOptions().isEmpty();
	}
	private void test_02_06() {
		//Navigiere zur Seite 03
		warte5Sekunden();
		driver.navigate().to("http://localhost:8081/beispielanwendung01/seite3.html");
		//Betaetige Submit und ueberpruefe ob Text im Alert korrekt ist
		driver.findElement(By.xpath("//*[@value='Speichern']")).click();//xpath von chrome kopieren und einfuegen, mit dem gleichen Wert oder mit "//*[@value='Wert vom Element (dass es ein bisschen einfacher waere)']"
		assert driver.switchTo().alert().getText().equals("Sind Sie sicher?");//switchTo() um zum Alert zu gehen und Zugang zu dem Text zu haben, und dann mit dem Text zwischen den alert('Text') vergleichen
		warte5Sekunden();
		driver.switchTo().alert().accept();
	}
	private void test_02_07() {		
		warte5Sekunden();
		driver.findElement(By.xpath("//*[@id=\"icp-touch-link-language\"]")).click();//xpath von chrome kopieren und einfuegen, mit dem gleichen Wert oder mit "//*[@value='Wert vom Element (dass es ein bisschen einfacher waere)']"
		warte5Sekunden();
		driver.findElement(By.xpath("//*[@id=\"customer-preferences\"]/div/div/div/div[1]/div[2]/div/label/i")).click();		
		warte5Sekunden();
		driver.findElement(By.xpath("//*[@id=\"icp-btn-save\"]/span/input")).click();		
	}
	
	
}
