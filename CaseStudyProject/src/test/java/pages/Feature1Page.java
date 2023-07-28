package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.TestBase;
import commonUtils.Utilities;

public class Feature1Page extends TestBase{
	
	@FindBy(xpath="//a[text()='Samsung galaxy s6']")
	WebElement item;
	
	@FindBy(xpath="//*[@class='btn btn-success btn-lg']")
	WebElement addToCart;
	
	@FindBy(id="cartur")
	WebElement cart;
	
	@FindBy(xpath="//table[@class='table table-bordered table-hover table-striped']")
	WebElement cartItems;
	
	public Feature1Page() {
		PageFactory.initElements(driver, this);
		
	}
			
	public void addItem(String check) {
		Assert.assertEquals(check, item.getText());
		item.click();
		Utilities.waitTillPageIsLoaded(10);
		addToCart.click();
		Alert alert=driver.switchTo().alert();
		alert.accept();
		
				
	}
	
	public void delteItem(String check) {
		cart.click();
	
		System.out.println(cartItems.getText());
		
	}
}