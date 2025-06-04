package Projects.AbstractComponents;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Projects.FrameWorkDevelopment.PageObjects.CheckoutPage;

public class OrderPage extends AbstractComponent{
	
WebDriver x;
	
	public OrderPage(WebDriver x) {
		super(x);
		this.x = x;
	}
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutele;
	
	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> productNamesintable;
	
	public Boolean VerifyOrderDisplay(String productName) {
		Boolean match = productNamesintable.stream().anyMatch(product-> product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	
	

	

}
