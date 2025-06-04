package Projects.StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Projects.FrameWorkDevelopment.PageObjects.CartPage;
import Projects.FrameWorkDevelopment.PageObjects.CheckoutPage;
import Projects.FrameWorkDevelopment.PageObjects.ConfirmationPage;
import Projects.FrameWorkDevelopment.PageObjects.LandingPage;
import Projects.FrameWorkDevelopment.PageObjects.ProductCatalog;
import Projects.TestComponents.BaseTest;

public class StepDefination extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalog productCataloge;
	public CartPage c;
	public CheckoutPage  v;
	public ConfirmationPage b;
	
	@Given("I landed on the login page")
	public void I_landed_on_ecommerce_page() throws IOException {
		
		landingPage = launchApplication();

	}
	
	//We Should write this sentence in generic form (regular expression) as we may get more data at runtime.
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logges_in_username_password(String username, String password) {
		
		productCataloge = landingPage.LogininApplication(username, password);
	}
	
	@When("^I add the product (.+) to Cart$")
	public void added_product_to_cart(String productName) {
		
		List<WebElement> products =  productCataloge.getProductsList();
		productCataloge.addProductToCart(productName);
	}
	
	//The following line can be written in When also And.
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submitOrder(String productName) {
		
		c = productCataloge.goToCartPage();
		
		Boolean match = c.VerifyProductDisplay(productName);
		v = c.goToCheckout();
		v.selectcountry("india");		
		b = v.submitorder();
	}
	
	//Method to take value from the table.
	@Then("^VERIFY (.+) is displayed$")
	public void c_message(String confirmation) {
		
		b.getConfirmationMessage();
		x.close();
	}
	
	//Method to take value from the feature file.
	//We can aslo use {string} in place of ^\"([^\"]*)\
 	@Then("^\"([^\"]*)\" message is dispalyed$")
	public void message_is_displayed(String strArg1) {
		Assert.assertEquals("Incorrect Email or Password", l.getErrorMessage());
	}
}
