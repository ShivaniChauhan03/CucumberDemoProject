package Action_Files;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Utils.BaseClass;

public class gmail_Action extends BaseClass {
	
	public gmail_Action() 
	{
		super();
		PageFactory.initElements(driver, this);
	}
	
	
	public static By emailField = By.cssSelector("input[type='email']");
	public static By nextBtn = By.xpath("//span[text()='Next']");
	public static By errorMsg = By.xpath("//div[text()='Enter an email or phone number']");
	
	
	
	public void verifyEmailfeildIsDisplayed() 
	{
		Assert.assertTrue(element(emailField).isDisplayed(), "**[ASSERT FAILED]: Email field is not displayed");
		logMessage("**[ASSERT PASSED]: Email field is displayed");
	}
	
	
	
	public void ClickOnNextButton() 
	{
		element(nextBtn).isDisplayed();
		logMessage("Next Button is displayed");
		
		element(nextBtn).click();
		logMessage("Click on Next Button");
		
	}
	
	public void verifyErrorMessage(String message) 
	{
		Assert.assertTrue(element(errorMsg).getText().trim().equals(message), "**[ASSERT FAILED]: Error message is not displayed as expected "+message);
		logMessage("**[ASSERT PASSED]: Error message is not displayed as expected "+message);
		
	}
	public void enterEmail(String email) 
	{
		element(emailField).sendKeys(email);	

	}

}
