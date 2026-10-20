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



public class QADemoProductNegative  extends BaseClass{
@Test(groups = {"Negative"},dataProvider = "systemData",dataProviderClass = DataProviderUtility.class)
	
	public void verifyProductAddedToCart(String Username, String Password) {
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(15));
		QADemoLoginpage login = new QADemoLoginpage(driver);
        QADemoProductpage product = new QADemoProductpage(driver);
        QADemoOrderplacedpage order = new QADemoOrderplacedpage(driver);
        QADemoCartpage cart = new QADemoCartpage(driver);
        
		login.login(Username, Password);
		wait.until(ExpectedConditions.urlContains("/catalog"));
		product.getProduct().click();
		
		product.getCartIcon().click();
		Assert.assertEquals(cart.getEmptyCartMessage().isDisplayed(), true);
		Reporter.log("Negative test passed: Product was not added without clicking Add to Car",true);
		
		order.getLogoutButton().click();
		
	}
	
	

}