package BOTPAssignment.SeleniumFrameworkDesign.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import BOTPAssignment.TestComponents.BaseTest;
import BOTPAssignment.TestComponents.BaseTest.ExcelFile;
import BOTPAssignment.pageobjects.OrderPage;
import BOTPAssignment.pageobjects.ProductCatalogue;

public class OrderHistoryTest extends BaseTest{
	@ExcelFile(value = "userdata.xlsx", sheetName = "Sheet1")
	@Test(dataProvider="excelData", dataProviderClass = BaseTest.class)
	
	public void OrderHistory(String email, String pass, String productName, String productname2) throws IOException
	{
		ProductCatalogue productCatalogue=landingpage.loginApplication(email,pass);
		OrderPage orderpage = productCatalogue.goToOrdersPage();
		Boolean match = orderpage.verifyOrderDisplay(productName);
		Assert.assertTrue(match);
	}

}
