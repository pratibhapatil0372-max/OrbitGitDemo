package BOTPAssignment.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BOTPAssignment.AbstarctComponents.AbstractComponent;

public class SearchProduct extends AbstractComponent{
	
	WebDriver driver;
	
	public SearchProduct(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div[class='py-2 border-bottom ml-3'] input[placeholder='search']")
	WebElement search;
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	@FindBy(css="button[class='btn w-10 rounded'] i[class='fa fa-shopping-cart']")
	WebElement addCart;
	
	By productsBy = By.cssSelector(".mb-3");
	//By addToCart = By.cssSelector(".card-body button:last-of-type");
	
	By addToCart = By.cssSelector("button[class='btn w-10 rounded'] i[class='fa fa-shopping-cart']");		
		
	public void searchProduct(String productName)
	{
		waitForElementToAppear(productsBy);
		search.sendKeys(productName + Keys.ENTER);
	}
	
	public List<WebElement> getListOfSearchedProduct()
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = getListOfSearchedProduct().stream().filter(product-> product.findElement(By.cssSelector(".card-body h5 b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addSearchedProductToCart(String productName)
	{
		WebElement prod = getProductByName(productName);
		waitForWebElementToAppear(prod);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);",addCart);
		waitForElementToAppear(addToCart);
		waitForWebElementToBeClickable(addToCart);
		prod.findElement(addToCart).click();
		waitForElementToDisappear(spinner);
	}
			
	
}
