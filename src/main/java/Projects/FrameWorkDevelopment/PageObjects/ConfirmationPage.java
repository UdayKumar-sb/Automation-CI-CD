package Projects.FrameWorkDevelopment.PageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Projects.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

	WebDriver x;
	
	
	public ConfirmationPage(WebDriver x) {
		super(x);
		this.x=x;
		PageFactory.initElements(x, this);
	}
	

//	String confirmmessage = x.findElement(By.cssSelector(".hero-primary")).getText();
//	Assert.assertTrue(confirmmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	
	@FindBy (css = ".hero-primary")
	WebElement confirmmessage;
	
	public String getConfirmationMessage() {
		
		return confirmmessage.getText();

	}

}
