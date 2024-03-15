package TestRunner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

//import org.junit.runner.RunWith;
//import cucumber.api.CucumberOptions;
//import cucumber.api.junit.Cucumber;


//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;


//@RunWith(Cucumber.class)
@CucumberOptions(
	//	features = ".//Features//Customers.feature",
		features = {".//Features//Login.feature",".//Features//Customers.feature"},
	//	features = ".//Features",
		glue = "StepDefination",
		dryRun = false,
		monochrome = true,
	//	tags = "@sanity",	// execute only those scenario which write under sanity tags
	//	plugin = {"pretty","html:target/cucumber-reports/custumer_result.html"}
		plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
//		plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
	)

public class Run extends AbstractTestNGCucumberTests {
	
//	This class will Empty.

}
