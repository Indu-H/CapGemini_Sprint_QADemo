
package ecom.BITM.QADemoCartscript;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    private WebElement openWebcam(QADemoProductpage product,
                                  WebDriverWait wait) {
        WebElement webcam = wait.until(
            ExpectedConditions.visibilityOf(product.getProduct())
        );

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            webcam
        );

        try {
            wait.until(
                ExpectedConditions.elementToBeClickable(webcam)
            ).click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                webcam
            );
        }

        wait.until(ExpectedConditions.urlContains("/products/"));
        return webcam;
    }

    private void addWebcamToCart(QADemoProductpage product,
                                 WebDriverWait wait) {
        if (!driver.getPageSource().contains("In Cart")) {
            wait.until(
                ExpectedConditions.elementToBeClickable(product.getAddToCart())
            ).click();
        }
    }

    @Test(
        groups = {"Smoke", "Functionality"},
        dataProvider = "systemData",
        dataProviderClass = DataProviderUtility.class
    )
    public void verifyProductAddedToCart(String Username, String Password) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        QADemoLoginpage login = new QADemoLoginpage(driver);
        QADemoProductpage product = new QADemoProductpage(driver);
        QADemoOrderplacedpage order = new QADemoOrderplacedpage(driver);
        QADemoCartpage cart = new QADemoCartpage(driver);

        login.login(Username, Password);
        wait.until(ExpectedConditions.urlContains("/catalog"));

        openWebcam(product, wait);
        addWebcamToCart(product, wait);

        wait.until(
            ExpectedConditions.elementToBeClickable(product.getCartIcon())
        ).click();

        wait.until(ExpectedConditions.urlContains("/cart"));

        Assert.assertTrue(
            wait.until(
                ExpectedConditions.visibilityOf(cart.getShoppingCartHeading())
            ).isDisplayed(),
            "Shopping Cart page was not displayed"
        );

        Reporter.log("Navigated to Cart Page Successfully", true);

        wait.until(
            ExpectedConditions.elementToBeClickable(cart.getClearCart())
        ).click();

        Reporter.log("Cart cleared successfully", true);

        wait.until(
            ExpectedConditions.elementToBeClickable(order.getLogoutButton())
        ).click();

        Reporter.log("Logout successful", true);
    }

    @Test(
        groups = {"Integration"},
        dataProvider = "systemData",
        dataProviderClass = DataProviderUtility.class
    )
    public void verifyCountinueShopping(String Username, String Password) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        QADemoLoginpage login = new QADemoLoginpage(driver);
        QADemoProductpage product = new QADemoProductpage(driver);
        QADemoOrderplacedpage order = new QADemoOrderplacedpage(driver);
        QADemoCartpage cart = new QADemoCartpage(driver);

        login.login(Username, Password);
        wait.until(ExpectedConditions.urlContains("/catalog"));

        openWebcam(product, wait);
        addWebcamToCart(product, wait);

        wait.until(
            ExpectedConditions.elementToBeClickable(product.getCartIcon())
        ).click();

        wait.until(ExpectedConditions.urlContains("/cart"));

        Assert.assertTrue(
            wait.until(
                ExpectedConditions.visibilityOf(cart.getShoppingCartHeading())
            ).isDisplayed(),
            "Shopping Cart page was not displayed"
        );

        wait.until(
            ExpectedConditions.elementToBeClickable(cart.getContinueshopping())
        ).click();

        wait.until(ExpectedConditions.urlContains("/catalog"));

        wait.until(
            ExpectedConditions.elementToBeClickable(product.getAddbutton())
        ).click();

        Reporter.log("Continued shopping successfully", true);

        wait.until(
            ExpectedConditions.elementToBeClickable(product.getCartIcon())
        ).click();

        wait.until(ExpectedConditions.urlContains("/cart"));

        Assert.assertTrue(
            wait.until(
                ExpectedConditions.visibilityOf(cart.getShoppingCartHeading())
            ).isDisplayed(),
            "Shopping Cart page was not displayed"
        );

        wait.until(
            ExpectedConditions.elementToBeClickable(cart.getClearCart())
        ).click();

        wait.until(
            ExpectedConditions.elementToBeClickable(order.getLogoutButton())
        ).click();

        Reporter.log("Logout successful", true);
    }

    @Test(
        groups = {"Integration"},
        dataProvider = "systemData",
        dataProviderClass = DataProviderUtility.class
    )
    public void verifyProceedToCheckout(String Username, String Password) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        QADemoLoginpage login = new QADemoLoginpage(driver);
        QADemoProductpage product = new QADemoProductpage(driver);
        QADemoOrderplacedpage order = new QADemoOrderplacedpage(driver);
        QADemoCartpage cart = new QADemoCartpage(driver);
        QADemoCheckoutpage checkout = new QADemoCheckoutpage(driver);

        login.login(Username, Password);
        wait.until(ExpectedConditions.urlContains("/catalog"));

        openWebcam(product, wait);
        addWebcamToCart(product, wait);

        wait.until(
            ExpectedConditions.elementToBeClickable(product.getCartIcon())
        ).click();

        wait.until(ExpectedConditions.urlContains("/cart"));

        Assert.assertTrue(
            wait.until(
                ExpectedConditions.visibilityOf(cart.getShoppingCartHeading())
            ).isDisplayed(),
            "Shopping Cart page was not displayed"
        );

        wait.until(
            ExpectedConditions.elementToBeClickable(
                cart.getProceedToCheckoutButton()
            )
        ).click();

        wait.until(ExpectedConditions.urlContains("/checkout"));

        Assert.assertTrue(
            driver.getCurrentUrl().contains("/checkout"),
            "Checkout page was not opened"
        );

        Reporter.log("Navigated to Checkout Page Successfully", true);

        wait.until(
            ExpectedConditions.elementToBeClickable(checkout.getBackToCartLink())
        ).click();

        wait.until(ExpectedConditions.urlContains("/cart"));

        wait.until(
            ExpectedConditions.elementToBeClickable(cart.getClearCart())
        ).click();

        wait.until(
            ExpectedConditions.elementToBeClickable(order.getLogoutButton())
        ).click();

        Reporter.log("Logout successful", true);
    }
}
