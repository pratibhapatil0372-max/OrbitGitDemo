package BOTPAssignment.SeleniumFrameworkDesign.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BOTPAssignment.TestComponents.BaseTest;
import BOTPAssignment.pageobjects.CartPage;
import BOTPAssignment.pageobjects.CheckOutPage;
import BOTPAssignment.pageobjects.ConfirmationPage;
import BOTPAssignment.pageobjects.LandingPage;
import BOTPAssignment.pageobjects.OrderPage;
import BOTPAssignment.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest{

	
	@Test(dataProvider="getDataFromSheet")
	public void submitOrder(String email, String pass, String productName, String productname2) {
					
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);		
		landingpage.loginApplication(email,pass);		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		productCatalogue.goToCartPage();		
		CartPage cartpage = new CartPage(driver);
		Boolean match = cartpage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkoutpage = cartpage.goToCheckOut();
		checkoutpage.selectCountry("India");
		ConfirmationPage confirmationpage =checkoutpage.submitOrder();
		String confirmmsg = confirmationpage.getConfirmationMsg();
		Assert.assertTrue(confirmmsg.equalsIgnoreCase("Thankyou for the order."));	

	}
	

}
