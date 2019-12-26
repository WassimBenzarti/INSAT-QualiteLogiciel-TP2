import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonPurchase {
	public static void main(String[] args) {
		ChromeDriver driver = new ChromeDriver();
		try {
			driver.navigate().to("https://www.amazon.fr/");
			WebDriverWait wait = new WebDriverWait(driver, 40);
			
			driver.manage().window().setSize(new Dimension(1366, 728));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Nouvel Apple MacBook Pro")));
			driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Macbook pro");
			driver.wait();
			driver.findElement(By.partialLinkText("Nouvel Apple MacBook Pro")).click();

			driver.wait(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// driver.close();
		}

	}
}
