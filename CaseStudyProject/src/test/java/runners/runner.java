package runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
//import pages.AonWrapReportsPage;

@CucumberOptions(
		publish = true,

		features ="src//test//resources//features//Feature1.feature",
		glue = {"stepDefs"},
		dryRun = false,
		monochrome = true,
		plugin = {"pretty",
				 "html:target/reports/HtmlReport.html",
				 "json:target/reports/JSONReport.json",
				 "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
		}//,
		//tags="@smoke"
		)
public class runner extends AbstractTestNGCucumberTests {
}