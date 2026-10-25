
package ecom.BITM.QADemoProductscript;

import java.time.Duration;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import ecom.BITM.QADemo.GenericUtility.BaseClass;
import ecom.BITM.QADemo.GenericUtility.DataProviderUtility;
import ecom.BITM.QADemo.GenericUtility.ScreenshotUtility;
import ecom.BITM.QADemo.POM.QADemoCartpage;
import ecom.BITM.QADemo.POM.QADemoLoginpage;
import ecom.BITM.QADemo.POM.QADemoOrderplacedpage;
import ecom.BITM.QADemo.POM.QADemoProductpage;

public class QADemoProductNegative extends BaseClass {

    @Test(
        groups = {"Negative"},
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

        WebElement webcam = product.getProduct();

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            webcam
        );

        try {
            wait.until(ExpectedConditions.elementToBeClickable(webcam)).click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                webcam
            );
        }

        wait.until(ExpectedConditions.urlContains("/products/"));

        wait.until(
            ExpectedConditions.elementToBeClickable(product.getCartIcon())
        ).click();

        Assert.assertTrue(
            wait.until(
                ExpectedConditions.visibilityOf(cart.getEmptyCartMessage())
            ).isDisplayed(),
            "Cart should be empty because Add to Cart was not clicked"
        );
        ScreenshotUtility.captureScreenshot(driver, "ProductNegative");

        Reporter.log(
            "Negative test passed: Cart is empty because Add to Cart was not clicked",
            true
        );

        order.getLogoutButton().click();
    }
}
