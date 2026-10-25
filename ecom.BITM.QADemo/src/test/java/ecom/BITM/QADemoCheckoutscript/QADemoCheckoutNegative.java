package ecom.BITM.QADemoCheckoutscript;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import ecom.BITM.QADemo.GenericUtility.BaseClass;
import ecom.BITM.QADemo.GenericUtility.DataProviderUtility;
import ecom.BITM.QADemo.GenericUtility.ScreenshotUtility;
import ecom.BITM.QADemo.POM.QADemoLoginpage;
import ecom.BITM.QADemo.POM.QADemoProductpage;
import ecom.BITM.QADemo.POM.QADemoCartpage;
import ecom.BITM.QADemo.POM.QADemoCheckoutpage;

public class QADemoCheckoutNegative extends BaseClass {

    private void clickWebcam(WebDriverWait wait) {
        By webcamLocator = By.xpath("//p[@data-testid='product-description-10']");

        WebElement webcam = wait.until(
                ExpectedConditions.visibilityOfElementLocated(webcamLocator));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", webcam);

        webcam = wait.until(
                ExpectedConditions.visibilityOfElementLocated(webcamLocator));

        try {
            wait.until(
                    ExpectedConditions.elementToBeClickable(webcamLocator)).click();
        } catch (WebDriverException e) {
            webcam = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(webcamLocator));

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();", webcam);
        }
    }

    @Test(
        groups = {"Negative"},
        dataProvider = "negativeCheckoutData",
        dataProviderClass = DataProviderUtility.class
    )
    public void verifyCheckoutWithEmptyDetails(
            String username,
            String password,
            String firstName,
            String lastName,
            String shippingAddress,
            String cardNumber,
            String expiryDate,
            String cvv,
            String nameOnCard) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        QADemoLoginpage login = new QADemoLoginpage(driver);
        QADemoProductpage product = new QADemoProductpage(driver);
        QADemoCartpage cart = new QADemoCartpage(driver);
        QADemoCheckoutpage checkout = new QADemoCheckoutpage(driver);

        login.login(username, password);

        wait.until(ExpectedConditions.urlContains("/catalog"));
        Reporter.log("Login successful", true);

        clickWebcam(wait);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        product.getAddToCart())).click();

        Reporter.log("Webcam added to cart", true);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        product.getCartIcon())).click();

        wait.until(ExpectedConditions.urlContains("/cart"));

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        cart.getProceedToCheckoutButton())).click();

        wait.until(
                ExpectedConditions.visibilityOf(
                        checkout.getCheckoutHeading()));

        Reporter.log("Checkout page opened", true);

        checkout.shippingInfo(firstName, lastName, shippingAddress);
        checkout.paymentInfo(cardNumber, expiryDate, cvv, nameOnCard);

        Reporter.log("Checkout fields left empty", true);

        checkout.clickPlaceOrderButton();

        Reporter.log("Place Order button clicked", true);

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/checkout"),
                "Checkout was submitted despite empty required fields");
        ScreenshotUtility.captureScreenshot(driver, "CheckoutNegative");

        Reporter.log(
                "Negative Checkout Test Passed: User remains on checkout",
                true);
    }
}
