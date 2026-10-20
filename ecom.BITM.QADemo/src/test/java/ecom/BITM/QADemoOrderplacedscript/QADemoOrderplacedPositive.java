package ecom.BITM.QADemoOrderplacedscript;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import ecom.BITM.QADemo.GenericUtility.BaseClass;
import ecom.BITM.QADemo.GenericUtility.DataProviderUtility;
import ecom.BITM.QADemo.POM.QADemoLoginpage;
import ecom.BITM.QADemo.POM.QADemoProductpage;
import ecom.BITM.QADemo.POM.QADemoCartpage;
import ecom.BITM.QADemo.POM.QADemoCheckoutpage;
import ecom.BITM.QADemo.POM.QADemoOrderplacedpage;

public class QADemoOrderplacedPositive extends BaseClass {

    @Test(
        groups = {"Functionality", "Integration","System"},
        dataProvider = "checkoutdata",
        dataProviderClass = DataProviderUtility.class
    )
    public void verifyMyOrders(
            String username,
            String password,
            String firstName,
            String lastName,
            String shippingAddress,
            String cardNumber,
            String expiryDate,
            String cvv,
            String nameOnCard) {

        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(15));

        QADemoLoginpage login = new QADemoLoginpage(driver);
        QADemoProductpage product = new QADemoProductpage(driver);
        QADemoCartpage cart = new QADemoCartpage(driver);
        QADemoCheckoutpage checkout = new QADemoCheckoutpage(driver);
        QADemoOrderplacedpage order = new QADemoOrderplacedpage(driver);

        login.login(username, password);
        wait.until(ExpectedConditions.urlContains("/catalog"));
        Reporter.log("Login successful", true);
        product.getProduct().click();
        product.getAddToCart().click();
        Reporter.log("Product added to cart", true);
        product.getCartIcon().click();
        wait.until(ExpectedConditions.urlContains("/cart"));
        cart.getProceedToCheckoutButton().click();
        checkout.getCheckoutHeading();
        checkout.shippingInfo(firstName, lastName, shippingAddress);
        checkout.paymentInfo(cardNumber, expiryDate, cvv, nameOnCard);
//        checkout.getPlaceOrderButton().click();
        checkout.clickPlaceOrderButton();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(order.getOrderConfirmedMessage())).isDisplayed(),"Order confirmation was not displayed");
        Reporter.log("Order confirmed successfully", true);
        order.getProfilebutton().click();
        order.getMyorders().click();
        wait.until(ExpectedConditions.urlContains("/orders"));
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(order.getMyorderstext())).isDisplayed(),"My Orders heading is not displayed");
        Reporter.log("My Orders page displayed successfully", true);
        order.getLogoutButton().click();
        Reporter.log("Logout successful", true);
    }


    @Test(
        groups = {"Functionality", "Integration"},
        dataProvider = "checkoutdata",
        dataProviderClass = DataProviderUtility.class
    )
    public void verifyContinueShopping(
            String username,
            String password,
            String firstName,
            String lastName,
            String shippingAddress,
            String cardNumber,
            String expiryDate,
            String cvv,
            String nameOnCard) {

        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(15));

        QADemoLoginpage login = new QADemoLoginpage(driver);
        QADemoProductpage product = new QADemoProductpage(driver);
        QADemoCartpage cart = new QADemoCartpage(driver);
        QADemoCheckoutpage checkout = new QADemoCheckoutpage(driver);
        QADemoOrderplacedpage order = new QADemoOrderplacedpage(driver);

        login.login(username, password);
        wait.until(ExpectedConditions.urlContains("/catalog"));
        Reporter.log("Login successful", true);
        product.getProduct().click();
        product.getAddToCart().click();
        product.getCartIcon().click();
        wait.until(ExpectedConditions.urlContains("/cart"));
        cart.getProceedToCheckoutButton().click();
        checkout.getCheckoutHeading();
        checkout.shippingInfo(firstName, lastName, shippingAddress);
        checkout.paymentInfo(cardNumber, expiryDate, cvv, nameOnCard);
//        checkout.getPlaceOrderButton().click();
        checkout.clickPlaceOrderButton();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(order.getOrderConfirmedMessage())).isDisplayed(),"Order confirmation was not displayed");
        Reporter.log("Order confirmed successfully", true);
        order.getProfilebutton().click();
        order.getMyorders().click();
        wait.until(ExpectedConditions.urlContains("/orders"));
        order.getMyorderstext();
        Reporter.log("My Orders page displayed", true);
        order.getOrderDetailsLink().click();
        Reporter.log("Order details page opened", true);
        //order.getOrderspagecontinueshopping().click();
        order.clickContinueShopping();
        wait.until(ExpectedConditions.urlContains("/catalog"));
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(product.getProductCatalogHeading())).isDisplayed(),"Product Catalog heading is not displayed");
        Reporter.log("Continue Shopping opened Product Catalog successfully",true);
        order.getLogoutButton().click();
        Reporter.log("Logout successful", true);
    }
}