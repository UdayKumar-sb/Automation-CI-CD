package Projects.FrameWorkDevelopment;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Projects.AbstractComponents.OrderPage;
import Projects.FrameWorkDevelopment.PageObjects.CartPage;
import Projects.FrameWorkDevelopment.PageObjects.CheckoutPage;
import Projects.FrameWorkDevelopment.PageObjects.ConfirmationPage;
import Projects.FrameWorkDevelopment.PageObjects.ProductCatalog;
import Projects.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData", groups="purchase")
	
		public void submittest(HashMap<String, String> input) throws IOException {
		
		ProductCatalog pd = l.LogininApplication(input.get("email"), input.get("password"));
		List<WebElement> products =  pd.getProductsList();
		pd.addProductToCart(input.get("product"));
		CartPage c = pd.goToCartPage();
		
		Boolean match = c.VerifyProductDisplay(productName);
		CheckoutPage v = c.goToCheckout();
		v.selectcountry("india");		
		ConfirmationPage b = v.submitorder();
		b.getConfirmationMessage();
	}
	
	@Test(dependsOnMethods = {"submittest"})
	public void OrderHistoryTest() {
		//ZARA COAT #
		
		ProductCatalog pd = l.LogininApplication("cpatel@gmail.com", "Chin@123");
		OrderPage o = pd.gotoOrdersPage();
		o.VerifyOrderDisplay(productName);
		Assert.assertTrue(o.VerifyOrderDisplay(productName));
	}
	

	@DataProvider
	public Object[][] getData() throws IOException {
		
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "cpatel@gmail.com");
//		map.put("password", "Chin@123");
//		map.put("product", "ZARA COAT 3");
		
		List<HashMap<String, String>> data =  getJsonDataToMao(System.getProperty("user.dir")+"\\src\\test\\java\\Projects\\Data\\PurchaseOrder.json");
		return new Object[][] { {data.get(0)}, {data.get(0)}   };
	}
}