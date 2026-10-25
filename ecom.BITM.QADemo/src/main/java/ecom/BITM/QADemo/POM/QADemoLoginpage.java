package ecom.BITM.QADemo.POM;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QADemoLoginpage {

    WebDriver driver;
    WebDriverWait wait;

    public QADemoLoginpage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href='/login' and @data-testid='navbar-signin-link']")
    private WebElement signInLink;

    @FindBy(name = "username")
    private WebElement emailField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@data-testid='login-submit-button']")
    private WebElement signInButton;

    public WebElement getSignInLink() {
        return signInLink;
    }

    public WebElement getEmailField() {
        return emailField;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getSignInButton() {
        return signInButton;
    }

    public void login(String email, String password) {
        if (!driver.getCurrentUrl().contains("/login")) {
            wait.until(ExpectedConditions.elementToBeClickable(signInLink)).click();
        }

        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.clear();
        emailField.sendKeys(email);

        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
    }
}