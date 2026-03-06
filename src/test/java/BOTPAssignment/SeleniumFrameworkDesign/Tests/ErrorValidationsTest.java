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
import BOTPAssignment.TestComponents.BaseTest.ExcelFile;
import BOTPAssignment.pageobjects.CartPage;
import BOTPAssignment.pageobjects.CheckOutPage;
import BOTPAssignment.pageobjects.ConfirmationPage;
import BOTPAssignment.pageobjects.LandingPage;
import BOTPAssignment.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationsTest extends BaseTest{

	
	@ExcelFile(value = "userdata_B.xlsx", sheetName = "Sheet1")
	@Test(dataProvider="excelData", dataProviderClass = BaseTest.class)
	
	public void submitOrder(String email, String pass) throws IOException{
						
		landingpage.loginApplication(email,pass);	
		Assert.assertEquals("Incorrect email or password.",landingpage.getErrorMessage());		

	}
	

}
