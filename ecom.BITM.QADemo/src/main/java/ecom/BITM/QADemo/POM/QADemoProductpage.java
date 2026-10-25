
package ecom.BITM.QADemo.POM;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QADemoProductpage {

    WebDriver driver;
    WebDriverWait wait;

    public QADemoProductpage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@data-testid='browse-products-button']")
    private WebElement productLink;

    @FindBy(xpath = "//h1[normalize-space()='Product Catalog']")
    private WebElement productCatalogHeading;

    @FindBy(xpath = "//a[@data-testid='navbar-cart-link']")
    private WebElement cartIcon;

    @FindBy(xpath = "//p[@data-testid='product-description-10']")
    private WebElement product;

    @FindBy(xpath = "//button[@data-testid='product-add-to-cart-button']")
    private WebElement addToCart;

    @FindBy(xpath = "//button[@data-testid='product-add-to-cart-3']")
    private WebElement addbutton;

    public WebElement getProductLink() {
        return wait.until(
            ExpectedConditions.elementToBeClickable(productLink));
    }

    public WebElement getProductCatalogHeading() {
        return wait.until(
            ExpectedConditions.visibilityOf(productCatalogHeading));
    }

    public WebElement getCartIcon() {
        return wait.until(
            ExpectedConditions.elementToBeClickable(cartIcon));
    }

    public WebElement getProduct() {
        return wait.until(
            ExpectedConditions.elementToBeClickable(product));
    }

    public WebElement getAddToCart() {
        return wait.until(
            ExpectedConditions.elementToBeClickable(addToCart));
    }

    public WebElement getAddbutton() {
        return wait.until(
            ExpectedConditions.elementToBeClickable(addbutton));
    }
}