package Projects.FrameWorkDevelopment;
import java.io.IOException;
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
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import Projects.FrameWorkDevelopment.PageObjects.CartPage;
import Projects.FrameWorkDevelopment.PageObjects.CheckoutPage;
import Projects.FrameWorkDevelopment.PageObjects.ConfirmationPage;
import Projects.FrameWorkDevelopment.PageObjects.LandingPage;
import Projects.FrameWorkDevelopment.PageObjects.ProductCatalog;
import Projects.TestComponents.BaseTest;
import Projects.TestComponents.RetryWithTestNG;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidations extends BaseTest{
	
	//Each method represents one test case if we mark it with @Test
	
	@Test(groups = {"ErrorHandling"}, retryAnalyzer = RetryWithTestNG.class)		//Test Case1
		public void LoginErrorValidation() throws IOException {
		
		String productName = "ZARA COAT 3";
		
		ProductCatalog pd = l.LogininApplication("cpat@gmail.com", "Chin@123");
		l.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.", l.getErrorMessage());
	}
	
	@Test		//Test Case2
	public void ProductValidation() throws IOException {
	
	String productName = "ZARA COAT 3";
	
	ProductCatalog pd = l.LogininApplication("cpatel@gmail.com", "Chin@123");

	List<WebElement> products =  pd.getProductsList();
	pd.addProductToCart(productName);
	CartPage c = pd.goToCartPage();
	Boolean match = c.VerifyProductDisplay("ZARA COAT 33+");
	
	}	
}