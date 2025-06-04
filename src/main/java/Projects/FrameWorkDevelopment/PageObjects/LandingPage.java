package Projects.FrameWorkDevelopment.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Projects.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver x;	//Passing webdriver from main test class.
	
	public LandingPage(WebDriver x) {		//We are catching the variable from the main page.
		//Constructor will execute first before anything.
		//Initialization happens from here before anything happens in this class.
		super(x);
		this.x = x;		//Assigning it from main test to the local variable.	//We are assigning the catched variable to the local variable using this keyword.
		PageFactory.initElements(x, this);	//Initializes the PageFactory for this class, enabling the use of annotations like @FindBy.
	}
	
	//WebElement userEmail = 	x.findElement(By.id("userEmail"));
	
	//PageFactory
	//This is the fastest and easiest way to write or assign variables, which in the background converts as a huge line and runs.
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement PassWord;
	
	@FindBy(css="input[type='submit")
	WebElement submit;
	
	@FindBy (css="[class*='flyInOut']")
	WebElement errorMessage;
	
	//Action class
	public ProductCatalog LogininApplication(String email, String password) {
		
		userEmail.sendKeys(email);
		PassWord.sendKeys(password);
		submit.click();
		ProductCatalog pd = new ProductCatalog(x);
		return pd;
	}
	
	public void goTo() {
		x.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage() {
		waitforWebelementtoappear(errorMessage);
		return errorMessage.getText();
	}
}
