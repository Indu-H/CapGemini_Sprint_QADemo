
package ecom.BITM.QADemo.POM;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QADemoOrderplacedpage {

    WebDriver driver;
    WebDriverWait wait;

    public QADemoOrderplacedpage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[text()='Order Confirmed!']")
    private WebElement orderConfirmedMessage;

    @FindBy(xpath = "//button[contains(.,'Logout')] | //a[contains(.,'Logout')] | //*[@aria-label='Logout']")
    private WebElement logoutButton;

    @FindBy(xpath = "//a[text()='My Orders']")
    private WebElement myorderslink;

    @FindBy(xpath = "//span[text()='standard_user']")
    private WebElement profilebutton;

    @FindBy(xpath = "//h1[@class='text-3xl font-bold text-slate-900']")
    private WebElement myorderspage;

    @FindBy(xpath = "//button[text()='Continue Shopping']")
    private WebElement orderspagecontinueshopping;

    @FindBy(xpath = "//a[contains(@href,'/orders/')]")
    private WebElement orderDetailsLink;

    public WebElement getOrderspagecontinueshopping() {
        return wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[text()='Continue Shopping']")
                )
        );
    }

    public WebElement getMyorders() {
        return wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[text()='My Orders']")
                )
        );
    }

    public WebElement getMyorderstext() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h1[@class='text-3xl font-bold text-slate-900']")
                )
        );
    }

    public WebElement getOrderConfirmedMessage() {
        return wait.until(
                ExpectedConditions.visibilityOf(orderConfirmedMessage)
        );
    }

    public WebElement getLogoutButton() {
        return wait.until(
                ExpectedConditions.elementToBeClickable(logoutButton)
        );
    }

    public WebElement getProfilebutton() {
        return wait.until(
                ExpectedConditions.elementToBeClickable(profilebutton)
        );
    }

    public WebElement getOrderDetailsLink() {
        return wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[contains(@href,'/orders/')]")
                )
        );
    }

    public void clickContinueShopping() {
        By continueShoppingLocator =
                By.xpath("//button[text()='Continue Shopping']");

        WebElement button = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        continueShoppingLocator
                )
        );

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                button
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        continueShoppingLocator
                )
        ).click();
    }
}
