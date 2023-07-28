package commonUtils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import base.TestBase;
import io.cucumber.java.Scenario;

public class Utilities extends TestBase{
	
	/*************************************************************************************
	 Function name	: verifyFileDownload
     Parameters		: String - download path of file
	 				  String - filename
	 Returns		: none
	 Description	: The following function verifies if a file is download to the given
	 				  path with the filename
	***************************************************************************************/
	public static boolean verifyFileDownload(String fileName) throws InterruptedException {
		Thread.sleep(3000);
		boolean flag = false;
		File dir = new File(System.getProperty("user.dir") + prop.getProperty("setdownloadpath"));
	    File[] dir_contents = dir.listFiles();
	    for (int i = 0; i < dir_contents.length; i++) {
	    	if (dir_contents[i].getName().equals(fileName)) {
	    		return flag=true;
            }
        }
	    return flag;
	}
	
	public static void generateScreenshot(Scenario scenario) throws IOException{
		try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
		// Create object of SimpleDateFormat class and decide the format
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");		 
		//get current date time with Date()
		Date date = new Date();		 
		//formatting date
		String date1= dateFormat.format(date);
		if(scenario.isFailed() || !(scenario.isFailed())){
			TakesScreenshot screen = (TakesScreenshot) driver;
			File file =screen.getScreenshotAs(OutputType.FILE);
			byte[] imgByte;
			imgByte = FileUtils.readFileToByteArray(file);
			scenario.attach(imgByte, "image/png",scenario.getName()+date1 );					
		}
	}
	
	/*************************************************************************************
	 * Function name 	: waitTillPageIsLoaded
	 * Parameters 		: int - timeout in seconds
	 * Returns 			: none
	 * Description : The following function makes the script wait till the 
	 * 					webpage is loaded using JavaScript executor
	 ***************************************************************************************/
	public static void waitTillPageIsLoaded(int timeout){
		boolean readyState = false;
		boolean ajaxState = false;
		boolean checkCondition = false;
		
		try {Thread.sleep(2000);} catch(InterruptedException e){e.printStackTrace();}	
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		for (int i=0; i<timeout; i++){
						
			readyState = js.executeScript("return document.readyState").toString().equals("complete");
			//ajaxState  = (boolean) ((JavascriptExecutor) driver).executeScript("return window.jQuery != undefined && jQuery.active === 0");
				
			checkCondition = readyState && ajaxState;
			
//			System.out.println("ReadyState = "+readyState);
//			System.out.println("AjaxState = "+ajaxState);
			
			if (checkCondition){
				System.out.println("Page has loaded");
				break;
			} else {
				try {Thread.sleep(1000);} catch(InterruptedException e){e.printStackTrace();}
//				System.out.println("Page has not loaded yet: " + i + " seconds");
			}
		}
		
	}
	
	/*************************************************************************************
	 Function name	: isElementPresent
	 Parameters		: String - xpath of Element
	 Returns		: boolean
	 Description	: The following function returns a boolean value based on presence of
	 				  the desired element
	***************************************************************************************/
	public static boolean isElementPresent(String xpath) {
		try {driver.findElement(By.xpath(xpath));} catch(NoSuchElementException e) {return false;}
		System.out.println("Element is found!");
		return true;
	}
	
}

