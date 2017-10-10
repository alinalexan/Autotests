package task4.QATestTask4.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import task4.QATestTask4.BaseScript;
import task4.QATestTask4.GeneralActions;
import task4.QATestTask4.model.ProductData;
import task4.QATestTask4.utils.Properties;

public class CreateProductTest extends BaseScript {
	
	WebDriver driver;
	GeneralActions generalActions;
	Actions act;
	JavascriptExecutor js;
	WebDriverWait wait;
	String addedName;
	String addedQty;
	String addedPrice;
	
	@Parameters("browser")
	@Test (priority=1)
    public void openNewProductPage(String browser){ 
		
		driver = setUp(browser);
		generalActions = new GeneralActions(driver);
    	generalActions.login("webinar.test@gmail.com", "Xcg7299bnSmMuRLp9ITw");
    	System.out.println("First step OK");

    	generalActions.navigateToProductsPage();
    	
    	generalActions.waitForContentLoad(driver);
    	System.out.println("Page title is " + driver.getTitle());
    	
    	WebElement newProduct = driver.findElement(By
    			.cssSelector("#page-header-desc-configuration-add > span"));
    	newProduct.click();
    	    	
    	generalActions.waitForContentLoad(driver);
	}
    	
    @Test (priority=2, dataProvider="generateProduct")	
    public void fillNewProductData(String prodName, Integer quantity, String price) {
    
    	WebElement productName = driver.findElement(By.id("form_step1_name_1"));
    	productName.sendKeys(prodName);
    	
    	WebElement productQuantity = driver.findElement(By.id("form_step1_qty_0_shortcut"));
    	productQuantity.sendKeys(Keys.BACK_SPACE); // method .clear() doesn't work :-(
    	productQuantity.sendKeys(quantity.toString());
    	
    	js = (JavascriptExecutor)driver;
    	js.executeScript("scroll(0,400);");
    	
    	WebElement productPrice = driver.findElement(By.id("form_step1_price_shortcut"));
    	act = new Actions(driver);
    	act.doubleClick(productPrice).build().perform(); // method .clear() doesn't work :-(
    	productPrice.sendKeys(Keys.BACK_SPACE);
    	productPrice.sendKeys(price);
    	
    	addedName = prodName; addedQty = quantity.toString(); addedPrice = price.replace(".", ","); 
    }
	
	@DataProvider
	public Object[][] generateProduct() {
		ProductData data = new ProductData();
		return new Object [][] {
				{data.generateName(), data.generateQuantity(), data.generatePrice()}
		};
	}
	
	@Test(priority=3)
	public void saveNewProduct(){
		driver.findElement(By.cssSelector(".switch-input")).click();
		driver.findElement(By.xpath("//*[@id='growls']")).click();
		driver.findElement(By.cssSelector(".growl-close")).click();
		
		// "Save" button
		driver.findElement(By.cssSelector("button.btn.btn-primary.js-btn-save")).click();

	}

	@Test(priority=4)
	public void verifyCreatedProductInList(){
		driver.navigate().to(Properties.getBaseUrl());
		
		WebElement allProducts = driver.findElement(By
			.cssSelector("a.all-product-link.pull-xs-left.pull-md-right.h4")); 
		
		js.executeScript("arguments[0].scrollIntoView(true);", allProducts);
		allProducts.click();
		
		WebElement createdProductName = driver.findElement(By
			.xpath("//div[@class='product-price-and-shipping' and contains(.,'"+addedPrice+"')]/parent::div//a"));
		WebElement createdProductPrice = driver.findElement(By
			.xpath("//div[@class='product-price-and-shipping' and contains(.,'"+addedPrice+"')]/parent::div//span"));	
		
		Assert.assertEquals(createdProductName.getText(), addedName, 
				"Product name in catalogue doesn't match");
		Assert.assertEquals(createdProductPrice.getText().substring(0,5).trim(), addedPrice, 
				"Product price in catalogue doesn't match");
		
		js.executeScript("arguments[0].scrollIntoView(true);", createdProductName);
		createdProductName.click();
	}
	
	@Test(dependsOnMethods="verifyCreatedProductInList")
	public void verifyDetailedProductInformation(){
		
		WebElement productDetailsName = driver.findElement(By
			.cssSelector("h1.h1"));
		WebElement productDetailsPrice = driver.findElement(By
				.cssSelector("div.product-price.h5"));		
		WebElement productDetailsQty = driver.findElement(By
				.xpath("//div[@class='product-quantities']/span"));

		Assert.assertTrue(productDetailsName.getText().equalsIgnoreCase(addedName), 
				"Product name in product information doesn't match");
		Assert.assertEquals(productDetailsPrice.getText().substring(0,5).trim(), addedPrice,
				"Product price in product information doesn't match");
		
		if (productDetailsQty.getText() != "100"){
			Assert.assertEquals(productDetailsQty.getText().substring(0,2).trim(), addedQty,
				"Product quantity in product information doesn't match");
		} else {
			Assert.assertEquals(productDetailsQty.getText().substring(0,3).trim(), addedQty, 
				"Product quantity in product information doesn't match");
		}
	}
	
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
