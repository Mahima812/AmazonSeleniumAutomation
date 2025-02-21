package automation.tests;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automation.TestComponents.BaseTest;
import automation.pageObjects.CartPage;
import automation.pageObjects.ProductPage;
import io.qameta.allure.Allure;



public class E2ETest extends BaseTest {
	
	@Test(dataProvider="data")
	public void addToCartTest(HashMap<String, String> input) throws IOException, InterruptedException {
		ProductPage productPage = new ProductPage(driver);
		CartPage cartPage = new CartPage(driver);
		productPage.selectProduct(input.get("productToAdd"));
		cartPage.addProductToCart(input.get("productToAdd"));
	}
	
	@DataProvider(name ="data")
    public Object[][] getData() throws IOException{
        List<HashMap<String, String>> data = getJsonDataToMap("C:\\Users\\m.suresh.shambharkar\\eclipse-workspace\\AmazonSeleniumAutomation\\src\\test\\java\\automation\\data\\AddToCart.json");

        // Convert list of HashMap to Object[][]
        Object[][] dataArray = new Object[data.size()][1];
        for (int i = 0; i < data.size(); i++) {
            dataArray[i][0] = data.get(i);
        }
        return dataArray;
    }
}
