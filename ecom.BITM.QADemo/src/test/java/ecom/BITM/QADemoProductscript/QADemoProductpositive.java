
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
import ecom.BITM.QADemo.POM.QADemoCartpage;
import ecom.BITM.QADemo.POM.QADemoLoginpage;
import ecom.BITM.QADemo.POM.QADemoOrderplacedpage;
import ecom.BITM.QADemo.POM.QADemoProductpage;

public class QADemoProductpositive extends BaseClass {

    @Test(
        groups = {"Smoke", "Functionality"},
        dataProvider = "systemData",
        dataProviderClass = DataProviderUtility.class
    )
    public void verifyProductCatalog(String Username, String Password) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        QADemoLoginpage login = new QADemoLoginpage(driver);
        QADemoProductpage product = new QADemoProductpage(driver);
        QADemoOrderplacedpage order = new QADemoOrderplacedpage(driver);

        login.login(Username, Password);
        wait.until(ExpectedConditions.urlContains("/catalog"));

        Reporter.log("Login successful", true);

        Assert.assertTrue(
            driver.getCurrentUrl().contains("/catalog"),
            "Login failed"
        );

        Assert.assertTrue(
            wait.until(
                ExpectedConditions.visibilityOf(
                    product.getProductCatalogHeading()
                )
            ).isDisplayed(),
            "Product Catalog is not displayed"
        );

        Reporter.log("Product Catalog displayed successfully", true);

        order.getLogoutButton().click();
    }

    @Test(
        groups = {"Functionality"},
        dataProvider = "systemData",
        dataProviderClass = DataProviderUtility.class
    )
    public void verifyProduct(String Username, String Password) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        QADemoLoginpage login = new QADemoLoginpage(driver);
        QADemoProductpage product = new QADemoProductpage(driver);
        QADemoOrderplacedpage order = new QADemoOrderplacedpage(driver);

        login.login(Username, Password);

        wait.until(ExpectedConditions.urlContains("/catalog"));

        WebElement productElement = product.getProduct();

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            productElement
        );

        try {
            wait.until(
                ExpectedConditions.elementToBeClickable(productElement)
            ).click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                productElement
            );
        }

        wait.until(ExpectedConditions.urlContains("/products/"));

        Assert.assertTrue(
            driver.getCurrentUrl().contains("products/webcam"),
            "Webcam product page was not displayed"
        );

        Reporter.log("Product webcam displayed", true);

        order.getLogoutButton().click();
    }

    @Test(
        groups = {"Integration"},
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

        WebElement productElement = product.getProduct();

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            productElement
        );

        try {
            wait.until(
                ExpectedConditions.elementToBeClickable(productElement)
            ).click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                productElement
            );
        }

        wait.until(ExpectedConditions.urlContains("/products/"));

        wait.until(
            ExpectedConditions.elementToBeClickable(product.getAddToCart())
        ).click();

        wait.until(
            ExpectedConditions.elementToBeClickable(product.getCartIcon())
        ).click();

        Assert.assertTrue(
            wait.until(
                ExpectedConditions.visibilityOf(cart.getCartItem())
            ).isDisplayed(),
            "Product was not added to cart"
        );

        Reporter.log("Successfully added product to cart", true);

        wait.until(
            ExpectedConditions.elementToBeClickable(cart.getDeleteicon())
        ).click();

        order.getLogoutButton().click();
    }
}
