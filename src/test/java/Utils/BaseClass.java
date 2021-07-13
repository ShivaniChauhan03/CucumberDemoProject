package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;


public class BaseClass {
	
	public enum Locators 
	{
	    id, name, classname, css,cssSelector, xpath, linktext;
	}
	
	 public static WebDriver driver;
	 public static Properties testData = new Properties();
	 static String parentWindow;
	
	public void initializeBrowser()
	{
		System.out.print("*************************************** Session Started ***************************************");
//		browser=System.getProperty("browser");
//		server=System.getProperty("server");
//		//sheetName=System.getProperty("sheetName");
//		
//		if(server==null || server.isEmpty())
//			server=readProperty("seleniumserver");
//		
//		if(browser==null || browser.isEmpty())
//			browser=readProperty("browser");
//
//		
//		logMessage("Server: "+server.toUpperCase());
//		logMessage("Browser: "+browser.toUpperCase());
//		
//		if(server.equalsIgnoreCase("local"))
//		{
//			if(browser.equalsIgnoreCase("chrome")) 
//			{
//				
							
				 System.setProperty("webdriver.chrome.driver","src/test/resources/Drivers/chromedriver.exe");
				 driver = new ChromeDriver();
			     
			     driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			     driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
//			}

//			else if(browser.equalsIgnoreCase("firefox"))
//			{
//				driver = setFirefoxDriver();
//			}
//			
//			else if(browser.equalsIgnoreCase("safari"))
//			{
//				driver = new SafariDriver();
//			}
//			
//			else if(browser.equalsIgnoreCase("IE") || browser.equalsIgnoreCase("Internet Explorer"))
//			{
//				System.setProperty("webdriver.ie.driver", "src/test/resources/SeleniumWebdrivers/IEDriverServer.exe");
//				driver = new InternetExplorerDriver();
//				cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
//		        cap.setCapability("ignoreZoomSetting", true);
//			}
//            else if(browser.equalsIgnoreCase("edge"))
//            {
//
//            System.setProperty("webdriver.edge.driver", "src/test/resources/SeleniumWebdrivers/msedgedriver.exe");
//             driver = new EdgeDriver();
//             cap.setCapability("browserstack.edge.enablePopups", false);
//           	}
        
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	
	
	public void launchApplication(String url) 
	{
        driver.get(url);
        logMessage("**[INFO]: Product URL :: " + url);
	}



	public static void logMessage(String msg)
	{
		Reporter.log(msg, true);
	}
	
//	@SuppressWarnings("unused")
//	private static WebDriver setFirefoxDriver()
//	{
//		System.setProperty("webdriver.gecko.driver","src/test/resources/SeleniumWebdrivers/geckodriver.exe");
//		File pathBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
//		FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
//    	FirefoxProfile options = new FirefoxProfile();
//    	return new FirefoxDriver();
//	}
	
	public void closeSession()
	{
		driver.quit();
		logMessage("*************************************** Session closed ***************************************");
	}
	
	
	public void quitSession()
	{
		driver.close();
		logMessage("*************************************** Window closed ***************************************");
	}
	
	public void launchProduct(String Product,String url) 
	{
        driver.get(url);
        logMessage("**[Info]: "+Product+" URL :: " + url);
	}

	private By getBy(String locatorType, String locatorValue) 
	{
        switch (Locators.valueOf(locatorType)) 
        {
            case id:
                return By.id(locatorValue);
            case xpath:
                return By.xpath(locatorValue);
            case css:
            case cssSelector:
                return By.cssSelector(locatorValue);
            case name:
                return By.name(locatorValue);
            case classname:
                return By.className(locatorValue);
            case linktext:
                return By.linkText(locatorValue);
            default:
                return By.id(locatorValue);
        }
    }
	
	protected By getLocator(By elementToken, String replacement) 
	{
        if (!replacement.isEmpty()) {
            String loc = elementToken.toString().replaceAll("\'", "\\\"");
            String type = loc.split(":", 2)[0].split(",")[0].split("\\.")[1];
            String variable = loc.split(":", 2)[1].replaceAll("\\$\\{.+?\\}", replacement);
            return getBy(type, variable);
        } 
        else {
            return elementToken;
        }
    }
	
	
	protected List<WebElement> elements(By elementToken) 
	{
        return driver.findElements(getLocator(elementToken, ""));
    }
	
	protected WebElement element(By elementToken) 
	{
		return driver.findElement(getLocator(elementToken, ""));
    }
	
	protected WebElement element(By elementToken, String replacement) 
	{
        return driver.findElement(getLocator(elementToken, replacement));
    }
	
	public static String readProperty(String property)
	{
		String value;
		try 
		{
			FileInputStream testdata = new FileInputStream("src/test/resources/TestData/testData.properties");
			try 
			{
				testData.load(testdata);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		value=testData.getProperty(property);
		return value;
	}
	
	public void closeCurrentWindow()
	{
		driver.close();
		logMessage("Closed current window");
		driver.switchTo().window(parentWindow);
		logMessage("Switched back to original window");
	}
	
	public void refreshPage()
	{
		driver.navigate().refresh();
		logMessage("Page is refreshed");
	}

	
	protected void hoverOnElement(WebElement e)
	{
		Actions ac=new Actions(driver);
		ac.moveToElement(e).build().perform();
	}
	
	
	
}
