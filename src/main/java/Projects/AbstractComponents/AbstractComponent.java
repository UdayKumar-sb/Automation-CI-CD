package Projects.AbstractComponents;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Projects.FrameWorkDevelopment.PageObjects.CartPage;

public class AbstractComponent {
	
	WebDriver x;	//This local opject we are creating it to make it accessible outside of the constructor.
	
	public AbstractComponent(WebDriver x) {
		
		this.x = x;
		PageFactory.initElements(x, this);
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement Header;
	
//	WebDriverWait wait = new WebDriverWait(x, Duration.ofSeconds(5));
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	//We are converting these lines of code.
	
	public void waitforelementtoappear(By findBy) {
		
		WebDriverWait wait = new WebDriverWait(x, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitforWebelementtoappear(WebElement findBy) {
		
		WebDriverWait wait = new WebDriverWait(x, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementToDisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(x, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(x.findElement(By.cssSelector(".ng-animating"))));
	}
	
	public CartPage goToCartPage() {
		cartHeader.click();
		CartPage c = new CartPage(x);
		return c;
	}
	
	public OrderPage gotoOrdersPage() {
		
		Header.click();
		OrderPage o = new OrderPage(x);
		return o;
		
	}

	
}

//This abstract parent class consists of all the reusabilty code, like wait methods, switching to frames, switching to windows, Java script executors, Alert handlings 
//visibility.