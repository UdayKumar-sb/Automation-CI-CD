package Projects.TestComponents;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Projects.FrameWorkDevelopment.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver x;
	public LandingPage l;

	public WebDriver initializeDriver() throws IOException {
		
		
		//There is a properties class in java, which reads the global properties.
		Properties prop = new Properties();
		FileInputStream f = new FileInputStream(System.getProperty("user.dir")
				+"\\src\\main\\java\\Projects\\Resources\\GlobalData.properties");
		prop.load(f);
		
		//Reading and deciding which one to use, either from global properties or system property.
		//This is nothing but looking for browser system property, if not found it will go ahead with
		String BrowserName = System.getProperty("browser")
				!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
		
		if(BrowserName.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
	
		WebDriverManager.chromedriver().setup();
		 x = new ChromeDriver();	//This driver we need to send it to the main class(for each class of main folder)
		
		}
		else if(BrowserName.equalsIgnoreCase("firefor")) {
			//Firefor
			 x = new FirefoxDriver();
		}
		else if(BrowserName.equalsIgnoreCase("edge")) {
			//Edge
			 x = new EdgeDriver();
		}
		
		x.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		x.manage().window().maximize();
		return x;

		}
	
	
	
	
	
		public List<HashMap<String, String>> getJsonDataToMao(String filePath) throws IOException {
		
		//Reads and converts Json to String
		String JsonContent = FileUtils.readFileToString(new File(filePath), 
				StandardCharsets.UTF_8);
		
		//Jackson API External Utility
		//Convert String to Hashmap
		//We need to get new dependency called Jackson Databind.
		
		//Converting String to HashMaps
		//Create two hashmaps, this line will create a 2 hashmaps in list and gives it back to us.
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(JsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});
		return data;}
		//data = a list of 2 hashmaps
		
		
		
		
		
		//Screenshot Utility
		public String getScreenshot(String testCaseName, WebDriver x) throws IOException {
			
			TakesScreenshot ts = (TakesScreenshot) x;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
			FileUtils.copyFile(source, file);
			return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		}
		
		
		
		
	@BeforeMethod(alwaysRun = true)
		public LandingPage launchApplication() throws IOException {
		
		x = initializeDriver();
		l = new LandingPage(x);
		l.goTo();
		return l;
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		x.close();
	}


}
