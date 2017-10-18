package task5.QATestTask5;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;

import task5.QATestTask5.utils.logging.EventHandler;

/**
 * Base script functionality, can be used for all Selenium scripts.
 */
public abstract class BaseTest {
    
    protected EventFiringWebDriver driver;
    protected GeneralActions actions;
	
    /**
    *
    * @param browser Driver type to use in tests.
    *
    * @return New instance of {@link WebDriver} object.
    */
   public WebDriver getDriver(String browser) {
       switch (browser) {
		   	case "android":
		   		Map<String, String> mobileEmulation = new HashMap<String, String>();
		   		mobileEmulation.put("deviceName", "Nexus 5");
		   		Map<String, Object> chromeOptions = new HashMap<String, Object>();
		   		chromeOptions.put("mobileEmulation", mobileEmulation);
		   		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		   		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		   		return new ChromeDriver(capabilities);
           case "firefox":
               System.setProperty(
                       "webdriver.gecko.driver",
                       getResource("/geckodriver.exe"));
               return new FirefoxDriver();
           case "ie":
           case "internet explorer":
               System.setProperty(
                       "webdriver.ie.driver",
                       getResource("/IEDriverServer.exe"));
               return new InternetExplorerDriver();
           case "chrome":
           default:
               System.setProperty(
                       "webdriver.chrome.driver",
                       getResource("/chromedriver.exe"));
               return new ChromeDriver();
       }
   }

   /**
    * @param resourceName The name of the resource
    * @return Path to resource
    */
   private String getResource(String resourceName) {
       try {
          return Paths.get(BaseTest.class.getResource(resourceName).toURI()).toFile().getPath();
       } catch (URISyntaxException e) {
           e.printStackTrace();
       }
       return resourceName;
   }

   /**
    * Prepares {@link WebDriver} instance with timeout and browser window configurations.
    *
    * Driver type is based on passed parameters to the automation project,
    * creates {@link ChromeDriver} instance by default.
    *
    */
   public WebDriver setUp (String browser) {
       driver = new EventFiringWebDriver(getDriver(browser));
       driver.register(new EventHandler());

       driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
       driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
   	   driver.manage().window().maximize();
       return driver;
   }


   @AfterClass (enabled = false)
   public void tearDown() {
       if (driver != null) {
           driver.quit();
       }
   }

}
