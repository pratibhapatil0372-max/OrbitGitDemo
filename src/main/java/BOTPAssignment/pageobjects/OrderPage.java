package BOTPAssignment.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BOTPAssignment.AbstarctComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".totalRow button")
	WebElement checkOutEle;
	By checkOutElem = By.xpath("//button[normalize-space()='Checkout']");
	
//	@FindBy(xpath="//button[normalize-space()='Checkout']")
//	WebElement checkOutEle;
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productsName;
	
	public boolean verifyOrderDisplay(String productName)
	{
		Boolean match = productsName.stream().anyMatch(cartprod->cartprod.getText().equalsIgnoreCase(productName)); 
		return match;
		
	}
	
	public CheckOutPage goToCheckOut()
	{		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);",checkOutEle);
		waitForElementToAppear(checkOutElem);
		checkOutEle.click();
		return new CheckOutPage(driver);
	}
}
