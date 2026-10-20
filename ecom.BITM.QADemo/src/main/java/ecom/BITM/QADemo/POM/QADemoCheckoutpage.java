//package ecom.BITM.QADemo.POM;
//
//import java.time.Duration;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class QADemoCheckoutpage {
//	WebDriver driver;
//    WebDriverWait wait;
//
//    
//    public QADemoCheckoutpage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        PageFactory.initElements(driver, this);
//    }
//
//    @FindBy(xpath = "//h1[@data-testid='checkout-heading']")
//    private WebElement checkoutHeading;
//
//    @FindBy(xpath = "//a[@data-testid='back-to-cart-link']")
//    private WebElement backToCartLink;
//
//    @FindBy(xpath = "//input[@data-testid='checkout-first-name']")
//    private WebElement firstNameField;
//
//    @FindBy(xpath = "//input[@data-testid='checkout-last-name']")
//    private WebElement lastNameField;
//
//    @FindBy(name = "address")
//    private WebElement shippingAddressField;
//
//    @FindBy(xpath = "//input[@data-testid='checkout-card-number']")
//    private WebElement cardNumberField;
//
//    @FindBy(name="expiry")
//    private WebElement expiryDateField;
//
//    @FindBy(name="cvv")
//    private WebElement cvvField;
//
//    @FindBy(name="nameOnCard")
//    private WebElement nameOnCardField;
//
//    @FindBy(xpath = "//button[@data-testid='place-order-button']")
//    private WebElement placeOrderButton;
//    
//    @FindBy(xpath = "//*[contains(normalize-space(.), 'Cart is empty')]")
//    private WebElement emptyCartMessage;
//
//    public WebElement getEmptyCartMessage() {
//        return emptyCartMessage;
//    }
//
//
//	public WebElement getCheckoutHeading() {
//		return checkoutHeading;
//	}
//
//	public WebElement getBackToCartLink() {
//		return backToCartLink;
//	}
//
//	public WebElement getFirstNameField() {
//		return firstNameField;
//	}
//
//	public WebElement getLastNameField() {
//		return lastNameField;
//	}
//
//	public WebElement getShippingAddressField() {
//		return shippingAddressField;
//	}
//
//	public WebElement getCardNumberField() {
//		return cardNumberField;
//	}
//
//	public WebElement getExpiryDateField() {
//		return expiryDateField;
//	}
//
//	public WebElement getCvvField() {
//		return cvvField;
//	}
//
//	public WebElement getNameOnCardField() {
//		return nameOnCardField;
//	}
//
//	public WebElement getPlaceOrderButton() {
//		return placeOrderButton;
//	}
//    
//    
//	public void shippingInfo(String FirstName,String LastName,String ShippingAddress) {
//		firstNameField.clear();
//		firstNameField.sendKeys(FirstName);
//		lastNameField.clear();
//		lastNameField.sendKeys(LastName);
//		shippingAddressField.clear();
//		shippingAddressField.sendKeys(ShippingAddress);
//		
//  }
//	
//	public void paymentInfo(String CardNumber,String ExpiryDate,String CVV,String NameonCard) {
//		cardNumberField.clear();
//		cardNumberField.sendKeys(CardNumber);
//		expiryDateField.clear();
//		expiryDateField.sendKeys(ExpiryDate);
//		cvvField.clear();
//		cvvField.sendKeys(CVV);
//		nameOnCardField.clear();
//		nameOnCardField.sendKeys(NameonCard);
//	}
//	public void clickPlaceOrderButton() {
//	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//
//	    WebElement button = wait.until(
//	        ExpectedConditions.visibilityOfElementLocated(
//	            By.xpath("//button[@data-testid='place-order-button']")
//	        )
//	    );
//
//	    ((JavascriptExecutor) driver).executeScript(
//	        "arguments[0].scrollIntoView({block: 'center'});", button
//	    );
//
//	    wait.until(ExpectedConditions.elementToBeClickable(button));
//
//	    button.click();
//	}
//
//}

package ecom.BITM.QADemo.POM;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QADemoCheckoutpage {
	WebDriver driver;
    WebDriverWait wait;

    
    public QADemoCheckoutpage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Checkout heading
    @FindBy(xpath = "//h1[@data-testid='checkout-heading']")
    private WebElement checkoutHeading;

    // Back to Cart
    @FindBy(xpath = "//a[@data-testid='back-to-cart-link']")
    private WebElement backToCartLink;

    @FindBy(xpath = "//input[@data-testid='checkout-first-name']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@data-testid='checkout-last-name']")
    private WebElement lastNameField;

    // Verify the address field's data-testid in DevTools
    @FindBy(name = "address")
    private WebElement shippingAddressField;

 

    @FindBy(xpath = "//input[@data-testid='checkout-card-number']")
    private WebElement cardNumberField;

    
    @FindBy(name="expiry")
    private WebElement expiryDateField;

    @FindBy(name="cvv")
    private WebElement cvvField;

    @FindBy(name="nameOnCard")
    private WebElement nameOnCardField;

    // Place Order button - confirmed from your screenshot
    @FindBy(xpath = "//button[@data-testid='place-order-button']")
    private WebElement placeOrderButton;
    
    @FindBy(xpath = "//*[contains(normalize-space(.), 'Cart is empty')]")
    private WebElement emptyCartMessage;
    
    @FindBy(xpath="//a[@class=\"text-sm hover:text-brand-400 transition-colors\" and text()='Cart']")
    private WebElement cartcc;

    public WebElement getCartcc() {
		return cartcc;
	}


	public WebElement getEmptyCartMessage() {
        return emptyCartMessage;
    }


	public WebElement getCheckoutHeading() {
		return checkoutHeading;
	}

	public WebElement getBackToCartLink() {
		return backToCartLink;
	}

	public WebElement getFirstNameField() {
		return firstNameField;
	}

	public WebElement getLastNameField() {
		return lastNameField;
	}

	public WebElement getShippingAddressField() {
		return shippingAddressField;
	}

	public WebElement getCardNumberField() {
		return cardNumberField;
	}

	public WebElement getExpiryDateField() {
		return expiryDateField;
	}

	public WebElement getCvvField() {
		return cvvField;
	}

	public WebElement getNameOnCardField() {
		return nameOnCardField;
	}

	
    
    
	public WebElement getPlaceOrderButton() {
		return wait.until(
		        ExpectedConditions.visibilityOf(placeOrderButton));
		
	}
	
	public void clickPlaceOrderButton() {

	    
	    WebElement button = wait.until(
	    	    ExpectedConditions.visibilityOf(placeOrderButton)
	    	);

	    	JavascriptExecutor js = (JavascriptExecutor) driver;

	    	js.executeScript(
	    	    "arguments[0].scrollIntoView({block:'center', inline:'nearest'});",
	    	    button
	    	);

	    	wait.until(ExpectedConditions.elementToBeClickable(button));

	    	try {
	    	    button.click();
	    	} catch (org.openqa.selenium.ElementClickInterceptedException e) {
	    	    // Fallback if another element intercepts the normal click
	    	    js.executeScript("arguments[0].click();", button);
	    	}
	}

	






	public void shippingInfo(String fn,String ln,String add) {
		firstNameField.clear();
		firstNameField.sendKeys(fn);
		lastNameField.clear();
		lastNameField.sendKeys(ln);
		shippingAddressField.clear();
		shippingAddressField.sendKeys(add);
		
  }
	
	public void paymentInfo(String cn,String expiry,String cvv,String name) {
		cardNumberField.clear();
		cardNumberField.sendKeys(cn);
		expiryDateField.clear();
		expiryDateField.sendKeys(expiry);
		cvvField.clear();
		cvvField.sendKeys(cvv);
		nameOnCardField.clear();
		nameOnCardField.sendKeys(name);
	}

}