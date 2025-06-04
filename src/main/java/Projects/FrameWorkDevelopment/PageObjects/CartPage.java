package Projects.FrameWorkDevelopment.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Projects.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver x;
	
	public CartPage(WebDriver x) {
		super(x);
		this.x = x;
	}
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutele;
	
	@FindBy(css = ".cartsection h3")
	private List<WebElement> productTitles;
	
	public Boolean VerifyProductDisplay(String productName) {
		Boolean match = productTitles.stream().anyMatch(product-> product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		checkoutele.click();
		return new CheckoutPage(x);
	}
	

}
