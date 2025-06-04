package Projects.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;

import Projects.Resources.ExtenReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	
	ExtentTest test;
	ExtentReports reports = ExtenReporterNG.getReportObject();
	
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	}

	
	
	
	
	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		
		//Attaching ScreenShot
		String filePath = null;
		
		//We are basically giving the life to the driver.
		
		//GetTestClass = It will get the class what the specific test is referring.
		//GetRealClass = From there it will actually go to the real class and gets the field of driver.
		//Fields are part of class not methods. 
		
		try {
			x = (WebDriver) result.getTestClass().getRealClass().getField("x").get(result.getInstance());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), x);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	
	
	
	
	@Override
	public void onTestSkipped(ITestResult result) {
	}

	
	
	
	@Override
	public void onTestStart(ITestResult result) {
		test = reports.createTest(result.getMethod().getMethodName());
		//Unique thread is assigned to the object to avoid con-currency issues.
		extentTest.set(test);
	}

	
	
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
		
	}
	
	
	
	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		reports.flush();
	}
	
}
