package task4.QATestTask4;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import task4.QATestTask4.utils.logging.EventHandler;

/**
 * Base script functionality, can be used for all Selenium scripts.
 */
public abstract class BaseScript {
    
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
          return Paths.get(BaseScript.class.getResource(resourceName).toURI()).toFile().getPath();
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
	   driver.manage().window().setPosition(new Point(0, 0));
	   driver.manage().window().setSize(new Dimension(1200, 700));
       return driver;
   }


   /**
    * Closes driver instance after test class execution.
    */
   @AfterClass (enabled = false)
   public void tearDown() {
       if (driver != null) {
           driver.quit();
       }
   }

}


