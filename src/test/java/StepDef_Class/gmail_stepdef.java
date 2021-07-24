package StepDef_Class;


import java.util.Iterator;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Action_Files.gmail_Action;
import Utils.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class gmail_stepdef extends BaseClass{
	
	gmail_Action gmailPage = new gmail_Action();
	
	@Given("Launch gmail url on browser")
	public void launch_gmail_url_on_browser() 
	{
		initializeBrowser();
		logMessage("Gmail launched successfully");
		
		super.launchApplication("https://gmail.com/");
		System.out.println("Gmail launched successfully");
	     
	}

	@Given("Verify that email field is displayed")
	public void verify_that_email_field_is_displayed() {
		
		gmailPage.verifyEmailfeildIsDisplayed();
		
		
		
	    
	}

	
	@Then("Click on next button")
	public void click_on_next_button() 
	{
		gmailPage.ClickOnNextButton();
	}
	
	
	@Then("^Verify the error message (.*) is displayed under email field$")
	public void verifyErrorMsg(String message) 
	{
	  gmailPage.verifyErrorMessage(message);
	}
	
	
	@When("^Enter an email (.*)$")
	public void enterEmail(String email)
	{
		gmailPage.enterEmail(email);
	}
	
}
