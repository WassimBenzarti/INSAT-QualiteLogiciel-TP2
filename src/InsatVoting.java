import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;

public class InsatVoting {
	public static void main(String[] args) {
		ChromeDriver driver = new ChromeDriver();
		driver.navigate().to("http://www.insat.rnu.tn/Fr/accueil_46_34");
		driver.manage().window().setSize(new Dimension(1366, 728));
	    driver.findElement(By.id("9")).click();
	    driver.findElement(By.xpath("//span[contains(.,'Votez')]")).click();
		
	    try {
			driver.wait(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			driver.close();
		}
	    
	}
	
	
}
