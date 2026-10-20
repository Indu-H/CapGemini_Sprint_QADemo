
package ecom.BITM.QADemoCartscript;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import ecom.BITM.QADemo.GenericUtility.BaseClass;
import ecom.BITM.QADemo.GenericUtility.DataProviderUtility;
import ecom.BITM.QADemo.POM.QADemoCartpage;
import ecom.BITM.QADemo.POM.QADemoCheckoutpage;
import ecom.BITM.QADemo.POM.QADemoLoginpage;
import ecom.BITM.QADemo.POM.QADemoOrderplacedpage;
import ecom.BITM.QADemo.POM.QADemoProductpage;

public class QADemoCartPositive extends BaseClass {

    @Test(
        groups = {"Smoke","Functionality"},
        dataProvider = "systemData",
        dataProviderClass = DataProviderUtility.class
    )
    public void verifyProductAddedToCart(String Username, String Password) {

        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(15));
        QADemoLoginpage login = new QADemoLoginpage(driver);
        QADemoProductpage product = new QADemoProductpage(driver);
        QADemoOrderplacedpage order = new QADemoOrderplacedpage(driver);
        QADemoCartpage cart = new QADemoCartpage(driver);

        login.login(Username, Password);
        wait.until(ExpectedConditions.urlContains("/catalog"));
        wait.until(ExpectedConditions.elementToBeClickable(product.getProduct())).click();
        if (!driver.getPageSource().contains("In Cart")) {
            wait.until(ExpectedConditions.elementToBeClickable(
                    product.getAddToCart())).click();
        }
        wait.until(ExpectedConditions.elementToBeClickable(product.getCartIcon())).click();
        wait.until(ExpectedConditions.urlContains("/cart"));

        Assert.assertTrue(cart.getShoppingCartHeading().isDisplayed(),"Shopping Cart page was not displayed");
        Reporter.log("Navigated to Cart Page Successfully", true);
        cart.getClearCart().click();
        Reporter.log("Cart cleared successfully", true);
        wait.until(ExpectedConditions.elementToBeClickable(order.getLogoutButton())).click();
        Reporter.log("Logout successful", true);
    }


    @Test(
        groups = {"Integration"},
        dataProvider = "systemData",
        dataProviderClass = DataProviderUtility.class
    )
    public void verifyCountinueShopping(String Username, String Password) {

        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(15));

        QADemoLoginpage login = new QADemoLoginpage(driver);
        QADemoProductpage product = new QADemoProductpage(driver);
        QADemoOrderplacedpage order = new QADemoOrderplacedpage(driver);
        QADemoCartpage cart = new QADemoCartpage(driver);

        login.login(Username, Password);
        wait.until(ExpectedConditions.urlContains("/catalog"));
        wait.until(ExpectedConditions.elementToBeClickable(product.getProduct())).click();
        if (!driver.getPageSource().contains("In Cart")) {
            wait.until(ExpectedConditions.elementToBeClickable(
                    product.getAddToCart())).click();
        }
        wait.until(ExpectedConditions.elementToBeClickable(product.getCartIcon())).click();
        wait.until(ExpectedConditions.urlContains("/cart"));
        Assert.assertTrue(cart.getShoppingCartHeading().isDisplayed());
        cart.getContinueshopping().click();
        wait.until(ExpectedConditions.urlContains("/catalog"));
        wait.until(ExpectedConditions.elementToBeClickable(product.getAddbutton())).click();
        Reporter.log("Continued shopping successfully", true);
        wait.until(ExpectedConditions.elementToBeClickable(product.getCartIcon())).click();
        wait.until(ExpectedConditions.urlContains("/cart"));
        Assert.assertTrue(cart.getShoppingCartHeading().isDisplayed(),"Shopping Cart page was not displayed");
        cart.getClearCart().click();
        wait.until(ExpectedConditions.elementToBeClickable(order.getLogoutButton())).click();
        Reporter.log("Logout successful", true);
    }

    @Test(
        groups = {"Integration"},
        dataProvider = "systemData",
        dataProviderClass = DataProviderUtility.class
    )
    public void verifyProceedToCheckout(
            String Username, String Password) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        QADemoLoginpage login = new QADemoLoginpage(driver);
        QADemoProductpage product = new QADemoProductpage(driver);
        QADemoOrderplacedpage order = new QADemoOrderplacedpage(driver);
        QADemoCartpage cart = new QADemoCartpage(driver);
        QADemoCheckoutpage checkout = new QADemoCheckoutpage(driver);

        login.login(Username, Password);
        wait.until(ExpectedConditions.urlContains("/catalog"));
        wait.until(ExpectedConditions.elementToBeClickable(product.getProduct())).click();
        if (!driver.getPageSource().contains("In Cart")) {
            wait.until(ExpectedConditions.elementToBeClickable(
                    product.getAddToCart())).click();
        }
        wait.until(ExpectedConditions.elementToBeClickable(product.getCartIcon())).click();
        wait.until(ExpectedConditions.urlContains("/cart"));
        Assert.assertTrue(cart.getShoppingCartHeading().isDisplayed());
        wait.until(ExpectedConditions.elementToBeClickable(cart.getProceedToCheckoutButton())).click();
        wait.until(ExpectedConditions.urlContains("/checkout"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/checkout"),"Checkout page was not opened");
        Reporter.log("Navigated to Checkout Page Successfully", true);
        checkout.getBackToCartLink().click();
        wait.until(ExpectedConditions.urlContains("/cart"));
        cart.getClearCart().click();
        wait.until(ExpectedConditions.elementToBeClickable(order.getLogoutButton())).click();
        Reporter.log("Logout successful", true);
    }
}
