package task2.QATestTask2;

import org.openqa.selenium.WebDriver;

import task2.QATestTask2.utils.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;

/**
 * Base script functionality, can be used for all Selenium scripts.
 */
public abstract class BaseScript {
    /**
     * @return New instance of {@link WebDriver} object. Driver type is based on passed parameters
     * to the automation project, returns {@link ChromeDriver} instance by default.
     */
    public static WebDriver getDriver() {
        String browser = Properties.getBrowser();
        switch (browser) {
        	case "firefox":
        		System.setProperty(
        				"webdriver.gecko.driver",
        				new File(BaseScript.class.getResource("/geckodriver.exe").getFile()).getPath());
        		return new FirefoxDriver();	
        	case "ie":
        		System.setProperty(
        				"webdriver.ie.driver",
        				new File(BaseScript.class.getResource("/IEDriverServer.exe").getFile()).getPath());
        		return new InternetExplorerDriver();
        	case "chrome":
        	default:
            	System.setProperty(
            			"webdriver.chrome.driver",
            			new File(BaseScript.class.getResource("/chromedriver.exe").getFile()).getPath());
            		return new ChromeDriver();
        }
        
//        throw new UnsupportedOperationException("Method doesn't return WebDriver instance");
        		    		
    }
    
//    public static void quitDriver (WebDriver driver) {
//    	driver.quit();
//    }
    

}


