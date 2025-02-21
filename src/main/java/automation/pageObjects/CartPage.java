package automation.pageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import automation.AbstractComponents.AbstractComponents;


public class CartPage extends AbstractComponents{

	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@title='Add to Shopping Cart' and @type='submit']")
	WebElement addToCartButton;
	
	@FindBy(xpath = "//input[contains(@aria-labelledby,'view-cart-button')]")
	WebElement viewCartButton;
	
	@FindBy(xpath = "//span[contains(@class,'product-title')]/span[contains(@class,'a-truncate-cut')]")
	List<WebElement> productsInCart;
	
	@FindBy(xpath = "//a[@aria-label='Exit this panel and return to the product page. ']")
	WebElement closeButton;
	
	public void addProductToCart(String productName) throws InterruptedException {
		switchWindowByTitle(productName);
		Thread.sleep(2000);
		scrollIntoView(addToCartButton);
		jsClick(driver, addToCartButton);
		closeButton.click();
		scrollIntoView(cartButton);
		waitForElementToBeClickable(10, cartButton);
		jsClick(driver, cartButton);
		for(WebElement e:productsInCart) {
			if(e.getText().equalsIgnoreCase(productName)) {
				System.out.print("Product added successfully");
			}
		}
	}
}
