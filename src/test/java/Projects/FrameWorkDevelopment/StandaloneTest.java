package Projects.FrameWorkDevelopment;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Projects.FrameWorkDevelopment.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {
	
	public static void main(String[] args) {
		
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver x = new ChromeDriver();	//This driver we need to send it to the main class(for each class of main folder)
		//and there receive it using constructor.
		
		//Putting implicit wait at global level to curb sync issues
		x.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Landing Page Details.
		x.get("https://rahulshettyacademy.com/client");
		
		//
		LandingPage l = new LandingPage(x);	//we are sending webdriver to the landing page and collecting it with constructor. 
		
		x.findElement(By.id("userEmail")).sendKeys("cpatel@gmail.com");
		x.findElement(By.id("userPassword")).sendKeys("Chin@123");
		x.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(x, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		//We are getting all the products with the tgname "mb-3" and storing them in products.
		List<WebElement> products = x.findElements(By.cssSelector(".mb-3"));
		
		//Here we are using another concept called streams and filtering the products based on the text.
		WebElement prod = products.stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//Writing code for explicit wait to load all elements on webpage
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container"))); 
		
		wait.until(ExpectedConditions.invisibilityOf(x.findElement(By.cssSelector(".ng-animating"))));
		x.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartproducts = x.findElements(By.cssSelector(".cartsection h3"));
		
		Boolean match = cartproducts.stream().anyMatch(cartproduct-> cartproduct.getText().equalsIgnoreCase(productName));
//		Assert.assertTrue(match);
		x.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(x);
		a.sendKeys(x.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		x.findElement(By.xpath("(//button[contains(@class, 'ta-item')]) [2]")).click();
		x.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmmessage = x.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
}
