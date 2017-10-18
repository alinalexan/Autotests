package task5.QATestTask5.utils.logging;

import org.openqa.selenium.Rotatable;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AddRotatable;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        System.setProperty(
              "webdriver.chrome.driver",
              new File(Test.class.getResource("/chromedriver.exe").getFile()).getPath()
        );

        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "Nexus 4");

        Map<String, Object> chromeOptions = new HashMap<>();
        chromeOptions.put("mobileEmulation", mobileEmulation);

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        capabilities.setCapability(CapabilityType.ROTATABLE, true);

        WebDriver driver = new ChromeDriver(capabilities);

        driver.get("http://m.rte.ie");
/*
        // Try and rotate
        Augmenter augmenter = new Augmenter();
        augmenter.addDriverAugmentation(CapabilityType.ROTATABLE, new AddRotatable());
        WebDriver augmentedDriver = augmenter.augment(driver);
        ScreenOrientation currentOrientation = ((Rotatable) augmentedDriver).getOrientation();
        System.out.println(String.format("Current Orientation is: %s", currentOrientation));

        driver.quit();*/
    }
}