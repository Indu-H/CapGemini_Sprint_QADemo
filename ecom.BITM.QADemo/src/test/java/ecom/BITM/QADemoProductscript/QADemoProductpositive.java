package ecom.BITM.QADemoProductscript;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import ecom.BITM.QADemo.GenericUtility.BaseClass;
import ecom.BITM.QADemo.GenericUtility.DataProviderUtility;
import ecom.BITM.QADemo.POM.QADemoCartpage;
import ecom.BITM.QADemo.POM.QADemoLoginpage;
import ecom.BITM.QADemo.POM.QADemoOrderplacedpage;
import ecom.BITM.QADemo.POM.QADemoProductpage;


public class QADemoProductpositive extends BaseClass {
	@Test(groups = {"Smoke","Functionality"},dataProvider = "systemData",dataProviderClass = DataProviderUtility.class)
	    public void verifyProductCatalog(String Username, String Password) {

	        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(15));
	        QADemoLoginpage login = new QADemoLoginpage(driver);
	        QADemoProductpage product = new QADemoProductpage(driver);
	        QADemoOrderplacedpage order = new QADemoOrderplacedpage(driver);

	        login.login(Username, Password);
	        Reporter.log("Login credentials entered", true);
	        wait.until(ExpectedConditions.urlContains("/catalog"));
	        Assert.assertTrue(driver.getCurrentUrl().contains("/catalog"),"Login failed");
	        Reporter.log("Login successful", true);
	        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(product.getProductCatalogHeading())).isDisplayed(),"Product Catalog is not displayed");
	        Reporter.log("Product Catalog displayed successfully", true);
	        order.getLogoutButton().click();
	        
	  }
	
	@Test(groups = {"Functionality"},dataProvider = "systemData",dataProviderClass = DataProviderUtility.class)
	
	public void verifyProduct(String Username, String Password) {
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(15));
		QADemoLoginpage login = new QADemoLoginpage(driver);
        QADemoProductpage product = new QADemoProductpage(driver);
        QADemoOrderplacedpage order = new QADemoOrderplacedpage(driver);
		
		login.login(Username, Password);
		wait.until(ExpectedConditions.urlContains("/catalog"));
		product.getProduct().click();
		Assert.assertEquals(driver.getCurrentUrl().contains("products/bluetooth-speaker"), true);
		Reporter.log("Product Bluetooth Speaker displayed",true);
		order.getLogoutButton().click();
		
	}
	
@Test(groups = {"Integration"},dataProvider = "systemData",dataProviderClass = DataProviderUtility.class)
	
	public void verifyProductAddedToCart(String Username, String Password) {
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(15));
		QADemoLoginpage login = new QADemoLoginpage(driver);
        QADemoProductpage product = new QADemoProductpage(driver);
        QADemoOrderplacedpage order = new QADemoOrderplacedpage(driver);
        QADemoCartpage cart = new QADemoCartpage(driver);
        
		login.login(Username, Password);
		wait.until(ExpectedConditions.urlContains("/catalog"));
		product.getProduct().click();
		product.getAddToCart().click();
		product.getCartIcon().click();
		Assert.assertEquals(cart.getCartItem().isDisplayed(), true);
		Reporter.log("Sucessfully added product added in cart",true);
		cart.getDeleteicon().click();
		order.getLogoutButton().click();
		
	}
	

}