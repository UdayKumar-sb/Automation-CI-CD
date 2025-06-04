package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//We are creating a test ng runner to run tests written using cucumber framework, since they use testng assertions.
//We need to provide the details of feature file and step-defination file.
//By-default the output of your cucumber will be in encoded form not readable, Use monochrome to make it to readable format.
//By default your testng runner will not read the testng code / methods in your classes, you need to to extend the AbstractTestNGCucumberTests.
//We can use tag names gives in feature files to run selective tests.

@CucumberOptions(features="src/test/java/cucumber", glue="Projects.StepDefinitions", monochrome=true, 
plugin= {"html:target/cucumber.html"}, tags="@Regression")
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
