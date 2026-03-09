package BOTPAssignment.SeleniumFrameworkDesign.Tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import BOTPAssignment.TestComponents.BaseTest;
import BOTPAssignment.TestComponents.BaseTest.ExcelFile;
import BOTPAssignment.pageobjects.ProductCatalogue;
import BOTPAssignment.pageobjects.SearchProduct;

public class SearchProductTest extends BaseTest{
	
	@ExcelFile(value = "userdata.xlsx", sheetName = "Sheet1")
	@Test(dataProvider="excelData", dataProviderClass = BaseTest.class)
	
	public void serchProduct(String email, String pass,String productname, String productname2) {
	
		ProductCatalogue productCatalogue=landingpage.loginApplication(email,pass);
		SearchProduct serachproduct = new SearchProduct(driver);
		serachproduct.searchProduct(productname2);
		List<WebElement> products = productCatalogue.getProductList();
		serachproduct.getProductByName(productname2);
		serachproduct.addSearchedProductToCart(productname2);
		
	}

}
