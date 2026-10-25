
package ecom.BITM.QADemoCheckoutscript;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriverException;
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

public class QADemoCheckoutPositive extends BaseClass {

    private void clickWebcam(WebDriverWait wait) {
        By webcamLocator =
                By.xpath("//p[@data-testid='product-description-10']");

        WebElement webcam = wait.until(
                ExpectedConditions.visibilityOfElementLocated(webcamLocator)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                webcam
        );

        webcam = wait.until(
                ExpectedConditions.visibilityOfElementLocated(webcamLocator)
        );

        try {
            wait.until(
                    ExpectedConditions.elementToBeClickable(webcamLocator)
            ).click();
        } catch (WebDriverException e) {
            webcam = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(webcamLocator)
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    webcam
            );
        }
    }

    @Test(
            groups = {"Smoke", "Functionality"},
            dataProvider = "systemData",
            dataProviderClass = DataProviderUtility.class
    )
    public void verifyLoginAndProductCatalog(
            String Username, String Password) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        QADemoLoginpage login = new QADemoLoginpage(driver);
        QADemoProductpage product = new QADemoProductpage(driver);
        QADemoOrderplacedpage order = new QADemoOrderplacedpage(driver);
        QADemoCartpage cart = new QADemoCartpage(driver);
        QADemoCheckoutpage checkout = new QADemoCheckoutpage(driver);

        login.login(Username, Password);
        Reporter.log("Login credentials entered", true);

        wait.until(ExpectedConditions.urlContains("/catalog"));
        Assert.assertTrue(
                driver.getCurrentUrl().contains("/catalog"),
                "Login failed"
        );

        clickWebcam(wait);
        product.getAddToCart().click();
        product.getCartIcon().click();

        wait.until(ExpectedConditions.urlContains("/cart"));
        cart.getProceedToCheckoutButton().click();

        checkout.getCheckoutHeading();
        Reporter.log("Checkout page opened", true);

        Assert.assertTrue(checkout.getBackToCartLink().isDisplayed(),
                "Back to Cart link is not displayed");
        Assert.assertTrue(checkout.getFirstNameField().isDisplayed(),
                "First name field is not displayed");
        Assert.assertTrue(checkout.getLastNameField().isDisplayed(),
                "Last name field is not displayed");
        Assert.assertTrue(checkout.getShippingAddressField().isDisplayed(),
                "Address field is not displayed");
        Assert.assertTrue(checkout.getCardNumberField().isDisplayed(),
                "Card number field is not displayed");
        Assert.assertTrue(checkout.getExpiryDateField().isDisplayed(),
                "Expiry field is not displayed");
        Assert.assertTrue(checkout.getCvvField().isDisplayed(),
                "CVV field is not displayed");
        Assert.assertTrue(checkout.getNameOnCardField().isDisplayed(),
                "Name on card field is not displayed");
        Assert.assertTrue(checkout.getPlaceOrderButton().isDisplayed(),
                "Place Order button is not displayed");

        Reporter.log("Smoke passed: all Checkout elements are displayed", true);

        checkout.getBackToCartLink().click();
        wait.until(ExpectedConditions.urlContains("/cart"));

        cart.getClearCart().click();
        order.getLogoutButton().click();
    }

    @Test(
            groups = {"System", "Integration"},
            dataProvider = "checkoutdata",
            dataProviderClass = DataProviderUtility.class
    )
    public void verifyCompleteOrderPlacement(
            String Username,
            String Password,
            String firstName,
            String lastName,
            String shippingAddress,
            String cardNumber,
            String expiryDate,
            String cvv,
            String nameOnCard) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        QADemoLoginpage login = new QADemoLoginpage(driver);
        QADemoProductpage product = new QADemoProductpage(driver);
        QADemoCartpage cart = new QADemoCartpage(driver);
        QADemoCheckoutpage checkout = new QADemoCheckoutpage(driver);
        QADemoOrderplacedpage order = new QADemoOrderplacedpage(driver);

        login.login(Username, Password);
        wait.until(ExpectedConditions.urlContains("/catalog"));
        Reporter.log("Login successful", true);

        clickWebcam(wait);
        product.getAddToCart().click();
        Reporter.log("Product added to cart", true);

        product.getCartIcon().click();
        wait.until(ExpectedConditions.urlContains("/cart"));

        cart.getProceedToCheckoutButton().click();
        wait.until(ExpectedConditions.urlContains("/checkout"));
        checkout.getCheckoutHeading();

        Reporter.log("Checkout page opened", true);

        checkout.shippingInfo(firstName, lastName, shippingAddress);
        checkout.paymentInfo(cardNumber, expiryDate, cvv, nameOnCard);
        checkout.clickPlaceOrderButton();

        Assert.assertTrue(
                wait.until(ExpectedConditions.visibilityOf(
                        order.getOrderConfirmedMessage())).isDisplayed(),
                "Order confirmation message is not displayed"
        );

        Reporter.log("Order confirmed successfully", true);
        order.getLogoutButton().click();
    }

    @Test(
            groups = {"Integration"},
            dataProvider = "checkoutdata",
            dataProviderClass = DataProviderUtility.class
    )
    public void verifybackwardnavigation(
            String Username,
            String Password,
            String firstName,
            String lastName,
            String shippingAddress,
            String cardNumber,
            String expiryDate,
            String cvv,
            String nameOnCard) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        QADemoLoginpage login = new QADemoLoginpage(driver);
        QADemoProductpage product = new QADemoProductpage(driver);
        QADemoCartpage cart = new QADemoCartpage(driver);
        QADemoCheckoutpage checkout = new QADemoCheckoutpage(driver);
        QADemoOrderplacedpage order = new QADemoOrderplacedpage(driver);

        login.login(Username, Password);
        wait.until(ExpectedConditions.urlContains("/catalog"));
        Reporter.log("Login successful", true);

        clickWebcam(wait);

        if (!driver.getPageSource().contains("In Cart")) {
            product.getAddToCart().click();
        }

        Reporter.log("Product is in the cart", true);

        product.getCartIcon().click();
        wait.until(ExpectedConditions.urlContains("/cart"));
        Reporter.log("Shopping cart opened", true);

        cart.getContinueshopping().click();
        wait.until(ExpectedConditions.urlContains("/catalog"));
        Reporter.log("Continued shopping successfully", true);

        product.getCartIcon().click();
        wait.until(ExpectedConditions.urlContains("/cart"));

        cart.getProceedToCheckoutButton().click();
        wait.until(ExpectedConditions.urlContains("/checkout"));
        Reporter.log("Navigated to checkout page", true);

        checkout.shippingInfo(firstName, lastName, shippingAddress);
        checkout.paymentInfo(cardNumber, expiryDate, cvv, nameOnCard);
        checkout.clickPlaceOrderButton();

        Assert.assertTrue(
                wait.until(ExpectedConditions.visibilityOf(
                        order.getOrderConfirmedMessage())).isDisplayed(),
                "Order confirmation message is not displayed"
        );

        Reporter.log("Order confirmed successfully", true);

        wait.until(
                ExpectedConditions.elementToBeClickable(order.getLogoutButton())
        ).click();

        Reporter.log("Logout successful", true);
    }
}
