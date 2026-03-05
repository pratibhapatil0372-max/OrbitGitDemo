package BOTPAssignment.AbstarctComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import BOTPAssignment.pageobjects.CartPage;
import BOTPAssignment.pageobjects.OrderPage;

public class AbstractComponent {
	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersHeader;
	
	By cartlink = By.cssSelector("[routerlink*='cart']");
	By toastMessage = By.cssSelector("#toast-container");

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
				
	}
	
	public void waitForWebElementToBeClickable(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(findBy));
				
	}
	
	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
		
	}
	
	public void waitForElementToDisappear(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public CartPage goToCartPage() 
	{
		waitForElementToDisappear(spinner);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		waitForWebElementToBeClickable(cartlink);
		js.executeScript("window.scrollTo(0, 0);");
		//js.executeScript("arguments[0].scrollIntoView({behavior:'instant', block:'start'});", cartlink);
		//js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", cartHeader);
		//js.executeScript("document.documentElement.scrollTop = 0;");
		waitForElementToAppear(cartlink);
		cartHeader.click();
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
	
	public OrderPage goToOrdersPage() 
	{		
		ordersHeader.click();
		OrderPage orderpage = new OrderPage(driver);
		return orderpage;
	}
	
}
