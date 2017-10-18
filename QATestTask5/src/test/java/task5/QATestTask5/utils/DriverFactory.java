package task5.QATestTask5.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {
    /**
     *
     * @param browser Driver type to use in tests.
     * @return New instance of {@link WebDriver} object.
     */
	public static WebDriver initDriver(String browser) {
        switch (browser) {
        	case "mobile":
        		
        		ChromeOptions options = new ChromeOptions();
        		options.setCapability("deviceName", "Google Nexus 5");
//        		options.addExtensions(new File("/path/to/extension.crx"));
//        		options.setBinary(new File("/path/to/chrome"));
        		return new ChromeDriver(options);

        		/**
        		Map<String, String> mobileEmulation = new HashMap<>();
        		mobileEmulation.put("deviceName", "Google Nexus 5");
        		Map<String, Object> chromeOptions = new HashMap<>();
        		DesiredCapabilities caps = DesiredCapabilities.chrome();
        		caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        		System.setProperty("webdriver.chrome.driver", 
        				new File(DriverFactory.class.getResource("/chromedriver.exe").getFile()).getPath());
        		return new ChromeDriver(new ChromeOptions(mobileEmulation));*/
//        		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        		ChromeOptions options = new ChromeOptions();
//        		options.addArguments("test-type");
//        		capabilities.setCapability("chrome.binary", "<Path to binary>");
//        		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//        		return new ChromeDriver(options);
        		
//        		Map<String, String> mobileEmulation = new HashMap<String, String>();
//        		mobileEmulation.put("deviceName", "Nexus 5");
//        		Map<String, Object> chromeOptions = new HashMap<String, Object>();
//        		chromeOptions.put("mobileEmulation", mobileEmulation);
//        		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
//        		return new ChromeDriver(ChromeOptions.CAPABILITY, chromeOptions);

            case "firefox":
                System.setProperty(
                        "webdriver.gecko.driver",
                        new File(DriverFactory.class.getResource("/geckodriver.exe").getFile()).getPath());
                return new FirefoxDriver();
            case "ie":
            case "internet explorer":
                System.setProperty(
                        "webdriver.ie.driver",
                        new File(DriverFactory.class.getResource("/IEDriverServer.exe").getFile()).getPath());
                DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
                capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
                capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                return new InternetExplorerDriver(new InternetExplorerOptions(capabilities));
//            case "phantomjs":
//                System.setProperty(
//                        "phantomjs.binary.path",
//                        new File(DriverFactory.class.getResource("/phantomjs.exe").getFile()).getPath());
//                return new PhantomJSDriver();
            case "chrome":
            default:
                System.setProperty(
                        "webdriver.chrome.driver",
                        new File(DriverFactory.class.getResource("/chromedriver.exe").getFile()).getPath());
                return new ChromeDriver();
        }
    }

    /**
     *
     * @param browser Remote driver type to use in tests.
     * @param gridUrl URL to Grid.
     * @return New instance of {@link RemoteWebDriver} object.
     */
    public static WebDriver initDriver(String browser, String gridUrl) {
        // TODO prepare capabilities for required browser and return RemoteWebDriver instance
        throw new UnsupportedOperationException();
    }
}
