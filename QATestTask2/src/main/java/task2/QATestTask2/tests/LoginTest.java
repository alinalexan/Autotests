package task2.QATestTask2.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import task2.QATestTask2.BaseScript;

public class LoginTest extends BaseScript {

	public static void main(String[] args) throws InterruptedException {
	        
	        WebDriver driver = getDriver();
	        driver.navigate().to("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");
	        Thread.sleep(1000);
	       
	        By emailLocator = By.id("email");
	        By passwordLocator = By.id("passwd");
	        By loginBtnLocator = By.name("submitLogin");
	        String email = "webinar.test@gmail.com";
	        String password = "Xcg7299bnSmMuRLp9ITw";	
	        
	     
	        driver.findElement(emailLocator).sendKeys(email);
	        driver.findElement(passwordLocator).sendKeys(password);
	        driver.findElement(loginBtnLocator).click();	
	        
	        Thread.sleep(1000);
	        By userIconLocator = By.id("employee_infos");
	        By logoutBtnLocator = By.id("header_logout");
	        
	        driver.findElement(userIconLocator).click();
	        driver.findElement(logoutBtnLocator).click();
	        
	        driver.quit();
	
	}

}
