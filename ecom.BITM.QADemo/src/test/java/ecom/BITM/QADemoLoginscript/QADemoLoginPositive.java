package ecom.BITM.QADemoLoginscript;

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

public class QADemoLoginPositive extends BaseClass {

    @Test(
        groups = {"Smoke","Functionality"},
        dataProvider = "systemData",
        dataProviderClass = DataProviderUtility.class
    )
    public void verifyLogin(String Username, String Password) throws InterruptedException {

        QADemoLoginpage login = new QADemoLoginpage(driver);
        QADemoOrderplacedpage op=new QADemoOrderplacedpage(driver);
        login.login(Username, Password);
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("/catalog"),"Login failed: URL does not contain /catalog");
        Reporter.log("Login successful. Product Catalog is displayed.", true);
        op.getLogoutButton().click();
    }
    @Test(
            groups = {"Integration"},
            dataProvider = "systemData",
            dataProviderClass = DataProviderUtility.class
        )
        public void verifyLoginProductCartIntegration(String username, String password) throws InterruptedException {

            WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(15));
            QADemoLoginpage login = new QADemoLoginpage(driver);
            QADemoProductpage product = new QADemoProductpage(driver);
            QADemoCartpage cart = new QADemoCartpage(driver);
            login.login(username, password);

            Thread.sleep(3000);
            Assert.assertTrue(driver.getCurrentUrl().contains("/catalog"),"Login failed: Product Catalog URL not found");
            Reporter.log("Login successful. Product Catalog opened.", true);
            Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(product.getProductCatalogHeading())).isDisplayed(),"Product Catalog heading is not displayed");
            Reporter.log("Product Catalog verified successfully", true);
            wait.until(ExpectedConditions.elementToBeClickable(product.getCartIcon())).click();
            Thread.sleep(2000);
            Assert.assertTrue(driver.getCurrentUrl().contains("/cart"),"Shopping Cart URL not found");
            Reporter.log("Shopping Cart page opened successfully", true);
            Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(cart.getEmptyCartMessage())).isDisplayed(),"Empty cart message is not displayed");
            Reporter.log("Login and Cart Integration Test Passed", true);
    }
}