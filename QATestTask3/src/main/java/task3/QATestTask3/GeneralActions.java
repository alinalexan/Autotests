package task3.QATestTask3;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import task3.QATestTask3.utils.Properties;

/**
 * Contains main script actions that may be used in scripts.
 */

public class GeneralActions {

    private WebDriverWait wait;
	private WebDriver driver;
	private JavascriptExecutor js;

    public GeneralActions(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Logs in to Admin Panel.
     * @param login
     * @param password
     */
    public void login(String email, String password) {

    	driver.navigate().to(Properties.getBaseAdminUrl());
    	
        By emailLocator = By.id("email");
        By passwordLocator = By.id("passwd");
        By loginBtnLocator = By.name("submitLogin");

        driver.findElement(emailLocator).sendKeys(email);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(loginBtnLocator).click();
    }

    /**
     * Adds new category in Admin Panel.
     * @param categoryName
     */
    public void createCategory(String categoryName) {

        WebElement catalogue = driver.findElement(By.id("subtab-AdminCatalog"));
        Actions action = new Actions(driver);
        action.moveToElement(catalogue).build().perform();
        
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement categories = wait.until(ExpectedConditions.elementToBeClickable(By
        		.xpath("//*[@id='subtab-AdminCategories']/a")));
        action.moveToElement(categories).click().build().perform();
        
        /**
         * Second version for opening Categories page
         */
        
//      By catalogueLocator = By.id("subtab-AdminCatalog");
//      By categoryLocator = By.cssSelector(".link-levelone.-active > ul > li:nth-child(2)");
//      
//      driver.findElement(catalogueLocator).click();
//      WebDriverWait wait = new WebDriverWait(driver, 10);
//      wait.until(ExpectedConditions.elementToBeClickable(categoryLocator));
//      driver.findElement(categoryLocator).click();
        
        By addCategoryLocator = By.id("page-header-desc-category-new_category");
        driver.findElement(addCategoryLocator).click();
        
        By categoryNameLocator = By.id("name_1");
        driver.findElement(categoryNameLocator).sendKeys(categoryName);
        
        wait = new WebDriverWait(driver, 10);
        WebElement saveBtnLocator = wait.until(ExpectedConditions
        		.elementToBeClickable(By.id("category_form_submit_btn")));
        
        js = (JavascriptExecutor)driver;
        js.executeScript("scroll(0,1200);");
    	saveBtnLocator.click();
        
        WebElement successMsgLocator = driver.findElement(By
        		.xpath("//div[@class=\"alert alert-success\"]"));
        System.out.println("Success message " + successMsgLocator.getText());

    }

    /**
     * Waits until page loader disappears from the page
     */
    public void waitForContentLoad(WebDriver driver) {
		js = (JavascriptExecutor) driver;
		if (js.executeScript("return document.readyState").toString().equals("complete")) {
			System.out.println("Page Is loaded.");
		}
    }
	
}
