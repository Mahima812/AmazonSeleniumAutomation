package automation.pageObjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.AbstractComponents.AbstractComponents;

public class ProductPage extends AbstractComponents {

	WebDriver driver;

	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h2[contains(@class,'color-base a-text-normal')]")
	List<WebElement> allProducts;
	
	@FindBy(xpath = "//input[contains(@id,'upper-bound-slider')]")
	WebElement priceSlider;

	public void goTo() {
		driver.get("https://www.amazon.in/");
	}

	public void searchForProduct(String productName) {
		searchTextBox.sendKeys(productName);
		searchButton.click();
		System.out.println("Clicked on search button");
	}
	
	public void selectProduct(String productName) throws InterruptedException {
		searchForProduct(productName);
		System.out.println("Looking for "+productName);
		for (WebElement e : allProducts) {
			if (e.getText().equalsIgnoreCase(productName)) {
				Thread.sleep(5000);
				JavascriptExecutor js = (JavascriptExecutor) driver;
		        js.executeScript("arguments[0].scrollIntoView(true);", e);
				System.out.println(productName+" found");
				AbstractComponents.jsClick(driver, e);
				break; // Added break to prevent clicking multiple products
			}
		}

	}
	
	
}
