package task4.QATestTask4.utils.logging;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class EventHandler implements WebDriverEventListener {

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        CustomReporter.log("Navigate to " + url);
    }

    @Override
    public void afterNavigateTo(String s, WebDriver webDriver) {
        System.out.println("Open url: " + s);
    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {
        CustomReporter.log("Navigate back");
    }

    @Override
    public void afterNavigateBack(WebDriver driver) {
        CustomReporter.log("Current URL: " + driver.getCurrentUrl());
    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {
        CustomReporter.log("Navigate forward");
    }

    @Override
    public void afterNavigateForward(WebDriver driver) {
        CustomReporter.log("Current URL: " + driver.getCurrentUrl());
    }

    @Override
    public void beforeNavigateRefresh(WebDriver driver) {
        CustomReporter.log("Refresh page");
    }


    @Override
    public void afterNavigateRefresh(WebDriver driver) {
        CustomReporter.log("Current URL: " + driver.getCurrentUrl());
    }

    @Override
    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        System.out.println("Search for element: " + by.toString());
    }

    @Override
    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
        if (webElement != null) {
            CustomReporter.log("Found element " + webElement.getTagName());
        }
    }

    @Override
    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
        System.out.println("Click on element: " + webElement.getTagName() + " " + webElement.getAttribute("name"));
    }

    @Override
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        System.out.println("Element successfully clicked");
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        String value = Arrays.stream(keysToSend).map(CharSequence::toString).collect(Collectors.joining());
        CustomReporter.log(String.format("Change value of %s: %s\n", element.getTagName(), value));
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        CustomReporter.log(String.format("Changed element " + element.getTagName()));
    }


    @Override
    public void beforeScript(String script, WebDriver driver) {
        CustomReporter.log("Execute script: " + script);
    }

    @Override
    public void afterScript(String script, WebDriver driver) {
        CustomReporter.log("Script executed");
    }

    @Override
    public void onException(Throwable throwable, WebDriver webDriver) {
    	// already logged by reporter
    }

	@Override
	public void afterAlertAccept(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterAlertDismiss(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeAlertAccept(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeAlertDismiss(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}
}

