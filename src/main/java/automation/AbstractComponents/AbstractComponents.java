package automation.AbstractComponents;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {
     static WebDriver driver;

    public AbstractComponents(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(xpath = "//input[@id='twotabsearchtextbox']")
    public WebElement searchTextBox;

    @FindBy(xpath = "//input[@id='nav-search-submit-button']")
    public WebElement searchButton;
    
    @FindBy(xpath = "//span[@class='nav-cart-icon nav-sprite']")
	public WebElement cartButton;
    
    
    public void scrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    public void switchWindowByTitle(String title) {
    	Set<String> windows = driver.getWindowHandles();
		for(String window:windows) {
			driver.switchTo().window(window);
			if(driver.getTitle().equals(title)) {
				return;
			}
		}
    }
    
    public void waitForElementToBeClickable(int seconds, WebElement element) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    public static void jsClick(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", element);
    }
    
    public static String getScreenshot(String testCaseName) throws IOException {
    	TakesScreenshot ts = (TakesScreenshot)driver;
    	File source = ts.getScreenshotAs(OutputType.FILE);
    	File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
    	FileUtils.copyFile(source, file);
    	return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
    }

}
