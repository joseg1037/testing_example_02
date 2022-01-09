package de.training.linkstesten;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinksCheckenSeite {

	private WebDriver driver;

	public LinksCheckenSeite(WebDriver driver) {
		this.driver = driver;
	}

	public boolean checkenSeiteLinks() {
		List<WebElement> links = driver.findElements(By.tagName("a"));
		String url = "";
		List<String> ungueltigeLinks = new ArrayList<String>();// URL von Links
		List<String> gueltigeLinks = new ArrayList<String>();// Auch URL von Links

		HttpURLConnection httpVerbindung = null; // Dass man sich mit http verbinden kann

		int antwortcode = 200;// code fuer die Verbindung mit der Seite, Verbidung ist in Ordnung und die
								// Anfrage wurde geschickt

		Iterator<WebElement> x = links.iterator(); // ein Iterator mit dem man die Liste iterieren kann

		while (x.hasNext()) {
			url = x.next().getAttribute("href");// bekommt das href Merkmal von jedem Link
			if (url == null || url.isEmpty()) {
				System.out.println("Url " + url + " wurde nicht konfiguriert oder ist leer.");
				continue;
			}
			try {
				httpVerbindung = (HttpURLConnection) (new URL(url).openConnection());

				httpVerbindung.setRequestMethod("HEAD");// HEAD IST WIE GET aber erbittet ein HEADER wo man den
				httpVerbindung.connect(); // Statuscode (z.B. 200) finden kann
				antwortcode = httpVerbindung.getResponseCode();
				if (antwortcode > 400/* Fehler des Clients oder des Servers */) {
					System.out.println("Fehler vom ungueltigen Link: -- " + url);
					ungueltigeLinks.add(url);
				} else {// Code ist nicht groesser als 400, also ist es gueltig
					System.out.println("Gueltiger Link: -- " + url);
					gueltigeLinks.add(url);
				}
			} catch (Exception ex) {
				System.out.println("Fehler: " + ex.getMessage()); // oder ex.printStackTrace();
			}
		}

		System.out.println("Zahl von gueltigen Links: " + gueltigeLinks.size());
		System.out.println("Zahl von ungueltigen Links: " + ungueltigeLinks.size());

		if (ungueltigeLinks.size() > 0) {// WENN ES UNGUELTIGE LINKS GIBT
			System.out.println("****FEHLER****");
			for (int y = 0; y < ungueltigeLinks.size(); y++) {
				System.out.println(ungueltigeLinks.get(y));// ZEIGE JEDEN UNGUELTIGEN LINK
			}
			return false;
		} else {
			return true;
		}
	}

}
