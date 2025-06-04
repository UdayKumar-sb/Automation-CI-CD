package Projects.FrameWorkDevelopment.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Projects.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	
	WebDriver x;
	
	public CheckoutPage(WebDriver x) {
		
		super(x);
		this.x=x;
		PageFactory.initElements(x, this);
	}
	
	@FindBy (css = "[placeholder='Select Country']")
	WebElement country;
	
	@FindBy (xpath = "(//button[contains(@class, 'ta-item')]) [2]")
	WebElement selectcountry;
	
	@FindBy (css = ".action__submit")
	WebElement submit;
	
	By results = (By.cssSelector(".ta-results"));
	
	public void selectcountry(String countryName) {
		
		Actions a = new Actions(x);
		a.sendKeys(country, countryName).build().perform();
		
		waitforelementtoappear(results);
		
		selectcountry.click();
		
	}
	
	public ConfirmationPage submitorder() {
		submit.click();
		return new ConfirmationPage(x);
	}
}
