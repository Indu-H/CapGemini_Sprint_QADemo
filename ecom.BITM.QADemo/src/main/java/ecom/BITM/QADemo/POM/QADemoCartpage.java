package ecom.BITM.QADemo.POM;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QADemoCartpage {

    WebDriver driver;
    WebDriverWait wait;

    public QADemoCartpage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(15));

        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[contains(text(),'Shopping Cart')]")
    private WebElement shoppingCartHeading;

    @FindBy(xpath = "//button[@data-testid='proceed-to-checkout-button']")
    private WebElement proceedToCheckoutButton;
    
    
    @FindBy(xpath = "//*[contains(normalize-space(.), 'Your cart is empty')]")
    private WebElement emptyCartMessage;
    
    @FindBy(xpath="//button[@class='p-2 text-red-500 hover:bg-red-50 rounded-lg transition-colors']")
    private WebElement deleteicon;
    
    
    
    @FindBy(xpath="//button[text()='Continue Shopping']")
    private WebElement continueshopping;
    
    @FindBy(xpath="//button[@data-testid='cart-item-increase-4']")
    private WebElement increaseProduct;
    
    @FindBy(xpath="//span[@class='w-10 text-center font-medium']")
    private WebElement number;
    
    @FindBy(xpath="//p[@data-testid='cart-item-description-4']")
    private WebElement cartItem;
    
    @FindBy(xpath="//button[text()='Clear Cart']")
    private WebElement clearCart;
    


	public WebElement getClearCart() {
		return wait.until(ExpectedConditions.elementToBeClickable(clearCart));
	}

	public WebElement getCartItem() {
		return cartItem;
	}

	public WebElement getIncreaseProduct() {
		return increaseProduct;
	}

	public WebElement getNumber() {
		return number;
	}

	public WebElement getShoppingCartHeading() {
		return shoppingCartHeading;
	}

	public void setShoppingCartHeading(WebElement shoppingCartHeading) {
		this.shoppingCartHeading = shoppingCartHeading;
	}

	public WebElement getProceedToCheckoutButton() {
		return proceedToCheckoutButton;
	}
	
    public WebElement getContinueshopping() {
		return continueshopping;
	}
    
    public WebElement getDeleteicon() {
		return wait.until(ExpectedConditions.elementToBeClickable(deleteicon));
	}

	public WebElement getEmptyCartMessage() {
        return emptyCartMessage;
    }


}