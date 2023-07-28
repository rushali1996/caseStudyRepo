package stepDefs;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;
import io.cucumber.java.Scenario;
import base.TestBase;
import commonUtils.Utilities;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Feature1Page;



public class Feature1stepDef extends TestBase {
	@Given("User is logged in to the {string}")
	public void user_is_logged_in_to_the(String module) throws MalformedURLException {
		initialize(module);
		Utilities.waitTillPageIsLoaded(10);
	}


@When("user add an {string} to cart")
public void user_add_an_to_cart(String str) {
    Feature1Page obj= new Feature1Page();
    obj.addItem(str);
}

@Then("user validates the {string} is present")
public void user_validates_the_is_present(String stri) {
	 Feature1Page obj= new Feature1Page();
	    obj.delteItem(stri);
}

}