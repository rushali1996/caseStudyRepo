package stepDefs;

import java.io.IOException;
import base.TestBase;
import commonUtils.Utilities;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;

public class Hooks extends TestBase{
		
//		@BeforeTest //("@start")
//		public void initializedriver() throws MalformedURLException {
//			System.out.println("initial");
//			initialize("gtas");
//		}
	
		@AfterStep
     	public void takescreenshot(Scenario scenario) throws IOException{
			Utilities.generateScreenshot(scenario);
     	}
		
		
		@After("@finish")
		public void tearDown() {
			System.out.println("Test Completed!");
			TestBase.closeAllBrowsers();
		}
}