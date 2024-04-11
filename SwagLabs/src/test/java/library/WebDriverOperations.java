package library;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

import objects.ProductPojo;
import testData.TestData;
import testcases.BaseClass;

public class WebDriverOperations extends BaseClass {

	FileOperations fo = new FileOperations();
	private Object FileUtils;
	@SuppressWarnings("deprecation")
	public void instantiateBrowser(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			Log.info("ChromeDriverPath:"+chromeDriverPath);
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			Log.info("Chromdriverpath is set");
			driver = new ChromeDriver();
			Log.info("Chromedriver is instantiated");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}		

	}
	
	
	public void navigateToUrl(String url) {
			driver.get(url);
			Log.info("Navigated to url:"+url);		
	}
	
	
	public void enterEdit(WebElement element, String data) {
		element.sendKeys(data);
		Log.info("Entered data:"+data+" in element:"+element);
	}
	
	public void click(WebElement element) {
		element.click();
		Log.info("Clicked on element:"+element);
	}
	
	public Boolean verifyElementIsDisplayed(WebElement element) {
		Boolean dispalyed = element.isDisplayed();
		Log.info("Element is displayed:"+ dispalyed + " Element:"+element);		
		return dispalyed;
	}
	
	public Boolean verifyElementTextIsMatching(WebElement element, String expectedString) {
		String actualText = element.getText();
		Boolean elementTextIsMatching = actualText.equals(expectedString);
		if(elementTextIsMatching) {
			Log.info("Element text is matching:" +expectedString);				
		} else
		{
			takeScreenshot();
			Log.error("Element text is not matching - Expected:"+expectedString+" Actual:"+actualText);	
		}
	
		return elementTextIsMatching;
	}	

	public void sleep(int number)
	{
		try {
			Thread.sleep(number*1000);
			Log.info("Waited for "+number+" of seconds");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<ProductPojo> returnAllListedProducts ()
	{
		ArrayList<ProductPojo> productsDisplayed = new ArrayList<ProductPojo>();

		try {
			
		List<WebElement> number_of_products = driver.findElements(By.xpath("//div[@class='inventory_list']//div[@class='inventory_item_label']/a"));
		List<WebElement> number_of_products_price = driver.findElements(By.xpath("//div[@class='inventory_list']//div[@class='inventory_item_price']"));
		for(int i=0; i< number_of_products.size(); i++) {
			String itemName = number_of_products.get(i).getText();
			String price = number_of_products_price.get(i).getText().substring(1);
			Log.info("Itemname:"+itemName+" price:"+price);
			ProductPojo listedProduct = new ProductPojo(itemName, Double.valueOf(price));
			productsDisplayed.add(listedProduct);
		}
		}
		catch(Exception exception) {
			Log.error(exception.toString());
		}
		
		
		return productsDisplayed;
		
	}
	
	
	public void selectDropDownItem(WebElement select, String valueToSelect) {
		Select dropDown = new Select(select);
		dropDown.selectByVisibleText(valueToSelect);
		Log.info("Selected: "+valueToSelect+" from dropdown");
	}
	
	
	public void addProductToCart(int number) {
		List<WebElement> addToCartButtons = driver.findElements(By.xpath("//button[contains(text(), 'Add to cart')]"));
		addToCartButtons.get(number).click();
	}
	
	public int returnNumberOfItemsInCart() {
		return driver.findElements(By.xpath("//div[@class='cart_list']//div[@class='cart_item']")).size();
	}

	public WebDriverWait explicitWait() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Long.valueOf(new TestData().explicitWaitTimeout)));
		return wait;
	}
	
	
	public void waitForElementTobeDisplayed(WebElement element) {
		explicitWait().until(ExpectedConditions.visibilityOf(element));
	}	
	
	public void quitBrowser()
	{
		try {
			if(!(driver==null)) {
				driver.quit();
				Log.info("Driver is quit");				
			}

		} catch (Exception e) {
			Log.error(e);
		}
	}
	
	
	public void takeScreenshot() {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = fo.createSceenshotFile();

		try {
			Files.copy(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
