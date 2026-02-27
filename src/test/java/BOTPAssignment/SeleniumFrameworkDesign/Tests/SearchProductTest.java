package BOTPAssignment.SeleniumFrameworkDesign.Tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import BOTPAssignment.TestComponents.BaseTest;
import BOTPAssignment.pageobjects.ProductCatalogue;
import BOTPAssignment.pageobjects.SearchProduct;

public class SearchProductTest extends BaseTest{
	
	@Test(dataProvider="getDataFromSheet")
	
	public void serchProduct(String email, String pass,String productname, String productname2) {
	
		ProductCatalogue productCatalogue=landingpage.loginApplication(email,pass);
		SearchProduct serachproduct = new SearchProduct(driver);
		serachproduct.searchProduct(productname2);
		List<WebElement> products = productCatalogue.getProductList();
		serachproduct.getProductByName(productname2);
		serachproduct.addSearchedProductToCart(productname2);
		
	}

}
