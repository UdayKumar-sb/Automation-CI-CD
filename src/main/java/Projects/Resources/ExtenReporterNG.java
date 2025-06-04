package Projects.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtenReporterNG {
	

	public static ExtentReports getReportObject() {
		
		String path = System.getProperty("user.dir") + "//reports/index.html";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);	
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Uday's Reports");
				
		ExtentReports reports = new ExtentReports();
		reports.attachReporter(reporter);
		reports.setSystemInfo("Tester", "Uday");
		return reports;
	}
}
