package BOTPAssignment.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import BOTPAssignment.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public String url;
	public LandingPage landingpage;
	DataFormatter formatter = new DataFormatter();
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
			
		switch (browserName) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			break;
			case "firefox":
				System.setProperty("webdriver.gecko.driver", "path/to/geckodriver.exe");
				driver = new FirefoxDriver();
	
			break;
			case "edge":
				System.setProperty("webdriver.edge.driver", "C://EdgeDriver.exe");
				WebDriver driver = new EdgeDriver();
			break;
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		return driver;
	
	}
	
	public String getUrl() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis1 = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\GlobalData.properties");
		prop.load(fis1);
		String url = prop.getProperty("url");
		return url;
		
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException 
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
		
	}
	
	@DataProvider
	public Object[][] getDataFromSheet() throws Exception 
	{
			
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\testdata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int rowcount = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(0);
		int colcount = row.getLastCellNum();
		Object data[][] = new Object[rowcount][colcount];
		
		for(int i = 0; i < rowcount - 1; i++) {
		    row = sheet.getRow(i + 1);           // get the row
		    if(row == null) {                     // skip if row is empty
		        continue;
		    }
		    for(int j = 0; j < colcount; j++) {
		        XSSFCell cell = row.getCell(j);
		        data[i][j] = (cell == null) ? "" : formatter.formatCellValue(cell);
		    }
		}
			
		return data;
	}
	
		
	@BeforeMethod
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		landingpage = new LandingPage(driver);
		url = getUrl();
		landingpage.goTo(url);
		return landingpage;
	}
	
	
	
	@AfterMethod
	public void tearDown() 
	{
		driver.close();
	}
	

}
