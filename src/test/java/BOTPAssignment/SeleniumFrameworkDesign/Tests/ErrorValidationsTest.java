package BOTPAssignment.SeleniumFrameworkDesign.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import BOTPAssignment.TestComponents.BaseTest;
import BOTPAssignment.pageobjects.CartPage;
import BOTPAssignment.pageobjects.CheckOutPage;
import BOTPAssignment.pageobjects.ConfirmationPage;
import BOTPAssignment.pageobjects.LandingPage;
import BOTPAssignment.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationsTest extends BaseTest{

//	@Test(dataProvider="getDataFromSheet")
//	public void submitOrder(String email, String pass, String productName, String productname2) throws IOException{
//						
//		landingpage.loginApplication("anshika@gmail.com","Anshika@04");	
//		Assert.assertEquals("Incorrect email or password.",landingpage.getErrorMessage());		
//
//	}
	
	@Test(dataProvider="getDataFromSheet")
	public void productErrorValidation(String email, String pass, String productName, String productname2) throws InterruptedException 
	{
		String product = productName;	
		System.out.println("The product name:"+product);
		ProductCatalogue productCatalogue = landingpage.loginApplication(email,pass);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(product);
		CartPage cartpage = productCatalogue.goToCartPage();
		Boolean match = cartpage.verifyProductDisplay(product);
		Assert.assertTrue(match);
				
	}

}
