package Projects.FrameWorkDevelopment.PageObjects;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Projects.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent{
	
	WebDriver x;
	
	public ProductCatalog(WebDriver x) {
		super(x);
		this.x = x;
		PageFactory.initElements(x, this);
	}
	
	// List<WebElement> products = x.findElements(By.cssSelector(".mb-3"));	This line of code we are converting.

	@FindBy(css = ".mb-3")
	List<WebElement> products;	//Gathers all the products and assigns it to products.
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;		//This is a webElement which appears while the products are being loaded.
	
	By products1 = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toast = By.cssSelector("#toast-container"); 
	
	//We just created an Action method te get the products list.
	public List<WebElement> getProductsList() {
		waitforelementtoappear(products1);
		return (products);
	}
	
	public WebElement getproductbyname(String productName) {
		
		WebElement prod = getProductsList().stream().filter(product-> 
		
		product.findElement(By.cssSelector("b")).getText().equals(productName))
				.findFirst()
				.orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) {
		
		WebElement prod = getproductbyname(productName);
				prod.findElement(addToCart).click();
				waitforelementtoappear(toast);
				waitForElementToDisappear(spinner);
	}
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
}
