package BOTPAssignment.SeleniumFrameworkDesign.Tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import BOTPAssignment.TestComponents.BaseTest;
import BOTPAssignment.TestComponents.BaseTest.ExcelFile;
import BOTPAssignment.pageobjects.CartPage;
import BOTPAssignment.pageobjects.ProductCatalogue;
import BOTPAssignment.pageobjects.LandingPage;

public class ProductValidation extends BaseTest {
	
	
	@ExcelFile(value = "userdata.xlsx", sheetName = "Sheet1")
	@Test(dataProvider="excelData", dataProviderClass = BaseTest.class)
	public void productErrorValidation(String email, String pass, String productName, String productname2) throws InterruptedException 
	{
		String product = productName;	
		System.out.println("The product name:"+product);
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		landingpage.loginApplication(email,pass);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(product);
		CartPage cartpage = productCatalogue.goToCartPage();
		Boolean match = cartpage.verifyProductDisplay(product);
		Assert.assertTrue(match);
				
	}
}
