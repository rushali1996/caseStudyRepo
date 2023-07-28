package base;

import static org.testng.Assert.assertTrue;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import commonUtils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
    static Utilities utility;
	public static ChromeOptions chromeoptns;
    
	public TestBase() {
		prop = new Properties();
		String path = System.getProperty("user.dir")
			 + "//src//test//resources//configFiles//config.properties";
		FileInputStream fin;
	 	try {
			fin =new FileInputStream(path);
			prop.load(fin);
	 	} catch (IOException e) {e.printStackTrace();}
	 	
		chromeoptns = new ChromeOptions();
		//Changing default download path for ChromeDriver
		String downloadpath = System.getProperty("user.dir") + prop.getProperty("setdownloadpath");
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("download.default_directory", downloadpath);
		chromeoptns.setExperimentalOption("prefs", prefs);
		chromeoptns.setAcceptInsecureCerts(true);
		chromeoptns.setPageLoadStrategy(PageLoadStrategy.NONE);
		chromeoptns.addArguments("--disable-notifications");
		chromeoptns.addArguments("--remote-allow-origins=*");
//		chromeoptns.addArguments("--headless");
	}
	
	// return driver in initialize functions
	public static WebDriver initialize(String module) throws MalformedURLException{
		String execumode = prop.getProperty("executionmode");
		String strBrowser = prop.getProperty("browser");
		
		if (execumode.equalsIgnoreCase("local")){	
			if (strBrowser.equalsIgnoreCase("chrome")){
				driver = new ChromeDriver(chromeoptns);
				driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(150));
			} else if (strBrowser.equalsIgnoreCase("edge")){
				driver = new EdgeDriver();
			} else if (strBrowser.equalsIgnoreCase("firefox")){
				FirefoxProfile profile = new FirefoxProfile();
				profile.setPreference("profile", "C:\\Users\\A0785476\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\xy1v8t5g.default");
				profile.setPreference("network.automatic-ntlm-auth.trusted-uris", ".aon.com");
				profile.setPreference("network.automatic-ntlm-auth.allow-non-fqdn", "true");
				profile.setPreference("network.negotiate-auth.trusted-uris", ".aon.com");
				profile.setPreference("network.negotiate-auth.allow-non-fqdn", "true");
				FirefoxOptions capabilities = new FirefoxOptions();
				capabilities.setProfile(profile);
				capabilities.setBinary("C:\\Users\\A0785476\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
				driver = new FirefoxDriver(capabilities);
			}
			try {loginAndVerifyPage(module);} catch (Exception e) {e.printStackTrace();}
		} else if (execumode.equalsIgnoreCase("remote")){
			String node = prop.getProperty("node");
			driver = new RemoteWebDriver(new URL(node), chromeoptns);
			try {loginAndVerifyPage(module);} catch (Exception e) {e.printStackTrace();}
		}
		return driver;		
	}
	
	public static void closeAllBrowsers(){
		try{driver.quit();} catch(Exception e){e.printStackTrace();}
	}
	
	public static void loginAndVerifyPage(String module) throws InterruptedException {
		//String actual_text = null;
		//String expected_text = null;
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
		driver.manage().window().maximize();
	    driver.manage().deleteAllCookies();
	    driver.get(prop.getProperty(module));
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
	    
	    
	    driver.findElement(By.id("login2")).click();
		driver.findElement(By.id("loginusername")).sendKeys("rushali");
		driver.findElement(By.id("loginpassword")).sendKeys("test@123");
		driver.findElement(By.xpath("//button[text()='Log in']")).click();
		
		
		//Setting value for actual and expected text to verify page based on key value
//		if(module.equalsIgnoreCase("application")) {
//			actual_text = driver.findElement(By.xpath("//a[@id='nava']")).getText();
//			expected_text = "Welcome";
//		} else {
//			System.out.println("Invalid module value");
//		}
//		
//		assertTrue(actual_text.contains(expected_text));
//		
//		if (actual_text.contains(expected_text)) {
//			System.out.println("Authentication successful, expected \""+module+"\" page is loaded!");
//		} else {
//			driver.navigate().refresh();
//			System.out.println("Authentication failed, expected \""+module+"\" page is not loaded!. Actual text: " +actual_text+ ", Expected text: " +expected_text);
//		}
	    
	}
	
}
	
	
	



	