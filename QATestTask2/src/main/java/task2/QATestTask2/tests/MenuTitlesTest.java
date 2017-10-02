package task2.QATestTask2.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import task2.QATestTask2.BaseScript;

public class MenuTitlesTest extends BaseScript{
	
    public static void main(String[] args) throws Exception {

        By emailLocator = By.id("email");
        By passwordLocator = By.id("passwd");
        By loginBtnLocator = By.name("submitLogin");
        String email = "webinar.test@gmail.com";
        String password = "Xcg7299bnSmMuRLp9ITw";
	
    	List<String> menuItemsText = new ArrayList<>();
    	WebDriver driver = getDriver();
    	
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");

        driver.findElement(emailLocator).sendKeys(email);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(loginBtnLocator).click();

        // counting menu items
        List<WebElement> menus = driver.findElements(By.xpath("//ul[@class='menu']/li"
        		+ "[ not (contains(@id, 'tab-SELL') or contains"
        		+ "(@id, 'tab-IMPROVE') or contains(@id, 'tab-CONFIGURE'))]"));
        for (WebElement element: menus) {
        	System.out.println(element.getText());
        	menuItemsText.add(element.getText());
        }
        System.out.println("Menu items total: " + menus.size());
        
        // iterating through menu
        for (int menu = 0; menu < menus.size(); menu++){
        	driver.findElement(By.linkText(menuItemsText.get(menu))).click();
        	System.out.println("Clicked at " + menuItemsText.get(menu));
        	String pageTitle = driver.getTitle();
        	System.out.println("Page title is           " + pageTitle);
        	driver.navigate().refresh();
        	String refreshed = driver.getTitle();
        	System.out.println("Refreshed page title is " + refreshed);
        	
			if (!refreshed.equals(pageTitle)){
				throw new Exception("Not the same page title after refresh");
			}
        }
        driver.quit();
        
    }
}