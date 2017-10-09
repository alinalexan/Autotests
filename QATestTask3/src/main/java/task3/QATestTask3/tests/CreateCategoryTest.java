package task3.QATestTask3.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import task3.QATestTask3.BaseScript;
import task3.QATestTask3.GeneralActions;

public class CreateCategoryTest extends BaseScript {

	public static void main(String[] args) throws InterruptedException {
		
         WebDriver driver = getConfiguredDriver();
         String categoryName = "Dolls";
        
         GeneralActions actions;
         actions = new GeneralActions(driver);
         
         actions.login("webinar.test@gmail.com", "Xcg7299bnSmMuRLp9ITw");

         actions.createCategory(categoryName);

         By sortingLocator = By.xpath("//span[contains(text(), 'Имя')]//i[@class='icon-caret-down']");
         driver.findElement(sortingLocator).click();
         
         actions.waitForContentLoad(driver);
         
         WebElement addedCategoryLocator = driver.findElement(By
        		 .xpath("//td[contains(text(),'" + categoryName + "')]"));
         
         System.out.println("My new category " + addedCategoryLocator.getText());
         
         driver.quit();

	}

}
