package BOTPAssignment.SeleniumFrameworkDesign.Tests;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import BOTPAssignment.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		
		LandingPage landingpage = new LandingPage(driver);
		
		//driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		
		driver.findElement(By.id("userEmail")).sendKeys("pratibha-rajkumar.patil@capgemini.com");
		driver.findElement(By.id("userPassword")).sendKeys("Pratibha@04");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
				
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		List<WebElement> cartprods = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartprods.stream().anyMatch(cartprod->cartprod.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		Thread.sleep(1000);
				
		WebElement element = driver.findElement(By.xpath("//div[@class='subtotal cf ng-star-inserted']/ul/li[3]"));
		 
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.cssSelector(".ta-results button:nth-of-type(2)")).click();
		driver.findElement(By.cssSelector(".action__submit ")).click();
		String confirmmsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmmsg.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
		

	}

}
