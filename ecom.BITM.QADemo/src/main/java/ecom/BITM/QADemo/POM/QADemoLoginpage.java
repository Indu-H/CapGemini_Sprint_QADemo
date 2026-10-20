//package ecom.BITM.QADemo.POM;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//
//public class QADemoLoginpage {
//	WebDriver driver;
//
//    public QADemoLoginpage(WebDriver driver) {
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
//    }
//
//    @FindBy(xpath = "//a[@href='/login' and  @data-testid='navbar-signin-link']")
//    private WebElement signInLink;
//
//    @FindBy(name="username")
//    private WebElement emailField;
//
//    @FindBy(name="password")
//    private WebElement passwordField;
//
//    @FindBy(xpath = "//button[@data-testid='login-submit-button']")
//    private WebElement signInButton;
//
//	public WebElement getSignInLink() {
//		return signInLink;
//	}
//
//	public WebElement getEmailField() {
//		return emailField;
//	}
//
//	public WebElement getPasswordField() {
//		return passwordField;
//	}
//
//	public WebElement getSignInButton() {
//		return signInButton;
//	}
//	
//	 public void login(String email, String password) {
//		 signInLink.click();
//		 emailField.clear();
//		 emailField.sendKeys(email);
//		 passwordField.clear();
//		 passwordField.sendKeys(password);
//		 signInButton.click();
//		   
//	 }
//}

package ecom.BITM.QADemo.POM;

import java.time.Duration;

import org.openqa.selenium.By;
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
        this.wait = new WebDriverWait(
            driver, Duration.ofSeconds(15)
        );
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath =
        "//a[@href='/login' and @data-testid='navbar-signin-link']")
    private WebElement signInLink;

    @FindBy(name = "username")
    private WebElement emailField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath =
        "//button[@data-testid='login-submit-button']")
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
    

//    public void login(String email, String password) {
//
//        // Navigate to the login page only if not already there.
//        if (!driver.getCurrentUrl().contains("/login")) {
//            driver.get("https://qademo.com/login");
//        }
//
//        // Wait for the login form to become available.
//        WebElement username = wait.until(
//            ExpectedConditions.visibilityOf(emailField)
//        );
//
//        WebElement passwordBox = wait.until(
//            ExpectedConditions.visibilityOf(passwordField)
//        );
//
//        username.clear();
//        username.sendKeys(email);
//
//        passwordBox.clear();
//        passwordBox.sendKeys(password);
//
//        wait.until(
//            ExpectedConditions.elementToBeClickable(signInButton)
//        ).click();
//
//        // Confirm navigation after login.
//        wait.until(
//            ExpectedConditions.urlContains("/catalog")
//        );
    

public void login(String email, String password) {
    WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(15));

    wait.until(ExpectedConditions.elementToBeClickable(signInLink))
        .click();

    // Re-locate fields after the page updates
    WebElement emailInput = wait.until(
        ExpectedConditions.visibilityOfElementLocated(
            By.name("username")
        )
    );

    emailInput.clear();
    emailInput.sendKeys(email);

    WebElement passwordInput = wait.until(
        ExpectedConditions.visibilityOfElementLocated(
            By.name("password")
        )
    );

    passwordInput.clear();
    passwordInput.sendKeys(password);

    wait.until(ExpectedConditions.elementToBeClickable(signInButton))
        .click();
}

    
    }

