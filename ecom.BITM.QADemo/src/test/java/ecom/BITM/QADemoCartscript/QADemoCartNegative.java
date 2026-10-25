package ecom.BITM.QADemoCartscript;

import java.time.Duration;

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
import ecom.BITM.QADemo.POM.QADemoProductpage;

public class QADemoCartNegative extends BaseClass {

    @Test(
        groups = {"Negative"},
        dataProvider = "systemData",
        dataProviderClass = DataProviderUtility.class
    )
    public void verifyProductIncrease(String Username, String Password) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        QADemoLoginpage login = new QADemoLoginpage(driver);
        QADemoProductpage product = new QADemoProductpage(driver);
        QADemoCartpage cart = new QADemoCartpage(driver);

        login.login(Username, Password);

        wait.until(ExpectedConditions.urlContains("/catalog"));

        WebElement webcam = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                org.openqa.selenium.By.xpath(
                    "//p[@data-testid='product-description-10']"
                )
            )
        );

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});", webcam
        );

        wait.until(ExpectedConditions.visibilityOf(webcam));

        Assert.assertTrue(webcam.isDisplayed(),
            "Webcam product 10 is not displayed");

        String cartCount = product.getCartIcon().getText().trim();

        Assert.assertTrue(
            cartCount.isEmpty() || cartCount.equals("0"),
            "Cart should be empty, but the cart shows: " + cartCount
        );
        ScreenshotUtility.captureScreenshot(driver, "CartNegative");

        Reporter.log(
            "Negative test passed: Cart remains empty without adding webcam product 10",
            true
        );
    }
}