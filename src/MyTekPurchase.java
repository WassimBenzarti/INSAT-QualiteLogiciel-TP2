import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyTekPurchase {
	static ChromeDriver driver = new ChromeDriver();
	static WebDriverWait wait = null;
	static JavascriptExecutor js = null;

	public static void main(String[] args) throws InterruptedException {
		// init the wait object
		wait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().setSize(new Dimension(1300, 700));
		js = (JavascriptExecutor) driver;
		try {
			// Navigate to the Website of MyTek
			driver.navigate().to("https://www.mytek.tn/");
			// Login to the website using credentials
			login();
			// Search and add to cart a product
			searchProductAndAddToCart("Carte Graphique PNY Geforce RTX 2080");
			// Finalize the purchase
			finalizePurchase();

		} finally {
			// Clean up
			driver.close();
		}
	}

	public static void login() {
		driver.findElement(By.linkText("Connexion")).click();
		waitForJQueryToLoad();
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).sendKeys("wass11121996@gmail.com");
		driver.findElement(By.id("passwd")).click();
		driver.findElement(By.id("passwd")).sendKeys("Azerty123");
		driver.findElement(By.cssSelector("#SubmitLogin > span")).click();
	}

	public static void searchProductAndAddToCart(String productName) throws InterruptedException {
		WebElement input = driver.findElement(By.id("search_query_top"));
		input.sendKeys(productName);
		input.submit();

		waitForJQueryToLoad();

		wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(productName)));
		WebElement product = driver.findElement(By.partialLinkText(productName));
		product.click();

		waitForJQueryToLoad();
		driver.findElement(By.cssSelector(".exclusive > span")).click();

		waitForJQueryToLoad();
		String passerCommandeXPath = "//a[@href='https://www.mytek.tn/commande' and @title='Passer la Commander']";
		driver.findElement(By.xpath(passerCommandeXPath)).click();

		waitForJQueryToLoad();
		scrollTo(0, 400);
		driver.findElement(By.xpath("//a[@href='https://www.mytek.tn/commande?step=1']")).click();

		waitForJQueryToLoad();
		scrollTo(0, 420);
		driver.findElement(By.xpath("//button[@name='processAddress']")).click();
		
		waitForJQueryToLoad();
		scrollTo(0, 420);
		driver.findElement(By.xpath("//button[@name='processCarrier']")).click();
		
		System.out.println("Done!");

	}

	public static void scrollTo(int x, int y) {
		// Scroll down a little bit
		js.executeScript("javascript:window.scrollBy(" + x + "," + y + ")"); // Since the element is hidden by the chat of MyTek																	
	}

	public static void finalizePurchase() {

	}

	// This function is helpful to track active Ajax requests since myTek is using jQuery Ajax
	public static void waitForJQueryToLoad() {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
		webDriverWait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
				.executeScript("return document.readyState").equals("complete"));

		webDriverWait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
				.executeScript("return !!jQuery ? jQuery.active==0 : true").equals(true));
	}

}
